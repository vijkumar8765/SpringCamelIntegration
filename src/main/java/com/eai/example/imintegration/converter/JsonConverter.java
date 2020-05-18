package com.eai.example.imintegration.converter;

import com.eai.example.imintegration.dto.EmployeeBackEndResponse;
import com.eai.example.imintegration.dto.EmployeeFrontEndRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JsonConverter {

    private ObjectMapper mapper;

    public JsonConverter() {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);
    }

    public EmployeeFrontEndRequest unmarshal(String json) throws IOException {
        return mapper.readValue(json, EmployeeFrontEndRequest.class);
    }

    public EmployeeBackEndResponse unmarshalBackEndResponse(String json) throws IOException {
        return mapper.readValue(json, EmployeeBackEndResponse.class);
    }

    public String marshal(EmployeeFrontEndRequest employeeFrontEndRequest) throws JsonProcessingException {
        return mapper.writeValueAsString(employeeFrontEndRequest);
    }

    public String marshalBackEndResponse(EmployeeBackEndResponse employeeBackEndResponse) throws JsonProcessingException {
        return mapper.writeValueAsString(employeeBackEndResponse);
    }
}
