package com.eai.example.imintegration.utils;

import com.eai.example.imintegration.fixtures.EmployeeServiceResponses;

import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;

public class EmployeeServiceMock extends JsonMock {

    public void returnsSuccessResponse(String url) {
        stubFor(endpoint(url)
                .willReturn(aSuccessResponse().withBody(EmployeeServiceResponses.success()))
        );
    }

    public void returnsEmptyResponse(String url) {
        stubFor(endpoint(url)
                .willReturn(aSuccessResponse().withBody(EmployeeServiceResponses.empty()))
        );
    }

}
