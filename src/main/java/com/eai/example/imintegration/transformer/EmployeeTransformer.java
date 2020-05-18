package com.eai.example.imintegration.transformer;

import com.eai.example.imintegration.converter.JsonConverter;
import com.eai.example.imintegration.dto.EmployeeBackEndResponse;
import com.eai.example.imintegration.dto.EmployeeFrontEndRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class EmployeeTransformer implements Processor {

    @Autowired
    JsonConverter jsonConverter;

    @Override
    public void process(Exchange exchange) throws IOException {
        log.info("EmployeeTransformer...Begin");
        try {
            String requestBody = exchange.getIn().getBody(String.class);
            System.out.println("requestBody======: " + requestBody);
            EmployeeFrontEndRequest request = jsonConverter.unmarshal(requestBody);
            System.out.println("request======: " + request);
            EmployeeBackEndResponse response = EmployeeBackEndResponse.builder()
                    .id(request.getId())
                    .name(request.getName())
                    .age(request.getAge())
                    .address("21, Wilson Street")
                    .dob("01-01-1988")
                    .build();

            String responseString =  jsonConverter.marshalBackEndResponse(response);
            System.out.println("responseString======: " + responseString);

            exchange.getIn().setHeader(Exchange.CONTENT_TYPE, "application/json");
            exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, 200);
            exchange.getIn().setBody(responseString);
        /*} else {
            String requestBody = exchange.getIn().getBody(String.class);
            System.out.println("requestBody======: " + requestBody);
            EmployeeFrontEndRequest employeeFrontEndRequest = jsonConverter.unmarshal(requestBody);
            System.out.println("employeeFrontEndRequest======: " + employeeFrontEndRequest);
            exchange.getIn().setBody(employeeFrontEndRequest);
            }*/
        } catch (Exception ex) {
            exchange.getIn().setHeader(Exchange.CONTENT_TYPE, "application/json");
            exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, 500);
//            exchange.setException(JsonValidationException.class);
        }


        log.info("EmployeeTransformer...End");
    }
}
