package com.eai.example.imintegration.processor;

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
public class FinalProcessor implements Processor {

    @Autowired
    JsonConverter jsonConverter;

    @Override
    public void process(Exchange exchange) throws IOException {
        log.info("FinalProcessor...Begin");
        try {
            String requestBody = exchange.getIn().getBody(String.class);
            System.out.println("requestBody======: " + requestBody);
            EmployeeBackEndResponse finalResponse = jsonConverter.unmarshalBackEndResponse(requestBody);
            System.out.println("finalResponse======: " + finalResponse);
            exchange.getIn().setBody(finalResponse);
        } catch (Exception ex) {
            exchange.getIn().setHeader(Exchange.CONTENT_TYPE, "application/json");
            exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, 500);
        }
        log.info("FinalProcessor...End");
    }
}
