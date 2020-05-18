package com.eai.example.imintegration.fixtures;

import com.eai.example.imintegration.converter.JsonConverter;
import com.eai.example.imintegration.dto.EmployeeBackEndResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

public class EmployeeServiceResponses {

    static public String success() {
        EmployeeBackEndResponse employeeBackEndResponse = new EmployeeBackEndResponse();
        employeeBackEndResponse.setId(1001);
        employeeBackEndResponse.setAge(21);
        employeeBackEndResponse.setName("Testing");
        employeeBackEndResponse.setAddress("21, Wilson Street");
        employeeBackEndResponse.setDob("01-01-1980");

        try {
            return new JsonConverter().marshalBackEndResponse(employeeBackEndResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    static public String empty() {
        return "";
    }
}
