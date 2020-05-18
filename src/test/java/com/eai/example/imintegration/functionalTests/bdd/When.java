package com.eai.example.imintegration.functionalTests.bdd;

import com.eai.example.imintegration.dto.EmployeeBackEndResponse;
import com.eai.example.imintegration.dto.EmployeeFrontEndRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.springframework.http.HttpMethod.POST;

public class When extends Bdd<When> {

    private final String url = "http://localhost:9090";
    private final RestTemplate httpClient = new RestTemplate();
    private static ResponseEntity<EmployeeBackEndResponse> responseEntity;
    private static ResponseEntity<Integer> responseEntityInt;

    public ResponseEntity<EmployeeBackEndResponse> saveEmployeeCall(EmployeeFrontEndRequest employeeFrontEndRequest) {
        RequestEntity requestEntity = new RequestEntity(employeeFrontEndRequest, headers(), POST, URI.create(url + "/saveEmployee"));
        responseEntity = httpClient.exchange(requestEntity, EmployeeBackEndResponse.class);
        return responseEntity;
    }

    private HttpHeaders headers() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-type", "application/json");
        return headers;
    }

}
