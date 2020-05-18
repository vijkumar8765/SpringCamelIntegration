package com.eai.example.imintegration.processor;

import com.eai.example.imintegration.converter.JsonConverter;
import com.eai.example.imintegration.dto.EmployeeFrontEndRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class EmployeeProcessor implements Processor {

    @Autowired
    JsonConverter jsonConverter;

    @Override
    public void process(Exchange exchange) throws IOException {
        log.info("EmployeeProcessor...Begin");
        try {
            if (exchange.getIn().getBody() instanceof EmployeeFrontEndRequest) {
                String json = jsonConverter.marshal(exchange.getIn().getBody(EmployeeFrontEndRequest.class));
                System.out.println("json======: " + json);
                exchange.getIn().setHeader(Exchange.CONTENT_TYPE, "application/json");
                exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, 200);
                exchange.getIn().setBody(json);
            } else {
                String requestBody = exchange.getIn().getBody(String.class);
                System.out.println("requestBody======: " + requestBody);
                EmployeeFrontEndRequest employeeFrontEndRequest = jsonConverter.unmarshal(requestBody);
                System.out.println("employeeFrontEndRequest======: " + employeeFrontEndRequest);
                exchange.getIn().setBody(employeeFrontEndRequest);
            }
        } catch (Exception ex) {
            exchange.getIn().setHeader(Exchange.CONTENT_TYPE, "application/json");
            exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, 500);
        }
        log.info("EmployeeProcessor...End");
    }
}
