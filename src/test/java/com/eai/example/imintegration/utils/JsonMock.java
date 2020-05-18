package com.eai.example.imintegration.utils;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public abstract class JsonMock {
    protected ResponseDefinitionBuilder aSuccessResponse() {
        return aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json");
    }

    protected ResponseDefinitionBuilder aFailedResponse() {
        return aResponse()
                .withStatus(500)
                .withHeader("Content-Type", "application/json");
    }

    protected MappingBuilder endpoint(String endpointURL) {
        return get(urlEqualTo(endpointURL))
                .withHeader("Accept", equalTo("application/json, application/*+json"));
    }

    protected MappingBuilder lgasendpoint(String endpointURL) {
        return post(urlEqualTo(endpointURL));
    }

}
