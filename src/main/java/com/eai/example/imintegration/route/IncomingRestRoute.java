package com.eai.example.imintegration.route;

import com.eai.example.imintegration.dto.EmployeeFrontEndRequest;
import com.eai.example.imintegration.processor.EmployeeProcessor;
import com.eai.example.imintegration.processor.FinalProcessor;
import com.eai.example.imintegration.transformer.EmployeeTransformer;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jsonvalidator.JsonValidationException;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static org.apache.camel.LoggingLevel.INFO;

@Component
public class IncomingRestRoute extends RouteBuilder {

    private static final String SAVE_EMPLOYEE_ROUTE = "direct:saveEmployee";
    private static final String VALIDATOR_FRONT_END_SCHEMA = "json-validator:classpath:schema/frontend-schema.json";
    private static final String VALIDATOR_BACK_END_SCHEMA = "json-validator:classpath:schema/backend-schema.json";

    @Value("${camel.restlet.port}")
    private Integer port;

    @Autowired
    EmployeeProcessor employeeProcessor;

    @Autowired
    EmployeeTransformer employeeTransformer;

    @Autowired
    FinalProcessor finalProcessor;

    @Override
    public void configure() throws Exception {

        restConfiguration()
            .component("restlet")
            .host("localhost")
            .port(port)
            .bindingMode(RestBindingMode.json)
            .dataFormatProperty("prettyPrint", "true");

        rest()
            .post("/saveEmployee")
            .type(EmployeeFrontEndRequest.class)
            .consumes("application/json")
            .route().routeId("save-employee")
            .log(INFO, "save-employee" + " Call Started..")
            .log(INFO, "Incoming headers: ====== " + simple("${headers}"))
            .end()
            .to(SAVE_EMPLOYEE_ROUTE)
            .log(INFO, "rest-save-employee" + " Call Completed..")
            .endRest();

        //formatter: off
        from(SAVE_EMPLOYEE_ROUTE)
            .log("SaveEmployee Route Started..")
            .errorHandler(defaultErrorHandler())
            .doTry()
                .process(employeeProcessor)
                .to(VALIDATOR_FRONT_END_SCHEMA)
                .process(employeeTransformer)
                .to(VALIDATOR_BACK_END_SCHEMA)
                .process(finalProcessor)
            .doCatch(JsonValidationException.class)
                .log("Exception Caught ========= ${exception.message}")
                .endDoTry()
            .log("SaveEmployee Route Completed..")
            .endDoTry();

    }
}
