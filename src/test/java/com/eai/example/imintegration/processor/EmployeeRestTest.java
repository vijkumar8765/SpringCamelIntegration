package com.eai.example.imintegration.processor;

import com.eai.example.imintegration.dto.EmployeeBackEndResponse;
import com.eai.example.imintegration.dto.EmployeeFrontEndRequest;
import com.eai.example.imintegration.functionalTests.bdd.When;
import org.apache.camel.CamelContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.*;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:functionalTest.properties")
@ComponentScan(basePackages = {"com.eai.example.imintegration"})
@ContextConfiguration(classes = EmployeeRestTest.class)
public class EmployeeRestTest {

    @Autowired
    protected CamelContext context;

    private When when;

    @Before
    public void setup() {
        when = new When();
    }

    /**
     * Success Test
     */
    @Test
    public void testSuccess() {

        EmployeeFrontEndRequest request =  EmployeeFrontEndRequest.builder()
                .id(1001)
                .age(23)
                .name("James Williams")
                .build();
        ResponseEntity<EmployeeBackEndResponse> result = when.saveEmployeeCall(request);
        EmployeeBackEndResponse response = result.getBody();
        assertNotNull(response);
        assertEquals("21, Wilson Street", response.getAddress());
        assertEquals("01-01-1988", response.getDob());
    }

    /**
     * id parameter in request payload is set as null
     *
     * throws JsonValidationError
     *
     */
    @Test
    public void testNullId() {

        try {
            EmployeeFrontEndRequest request = new EmployeeFrontEndRequest();
            request.setId(null);
            request.setAge(23);
            request.setName("James Williams");
            ResponseEntity<EmployeeBackEndResponse> result = when.saveEmployeeCall(request);
            EmployeeBackEndResponse response = result.getBody();
            Assert.assertNotNull(response);
            assertNull(response.getId());
            assertEquals(new Integer(23), response.getAge());
            assertEquals("21, Wilson Street", response.getAddress());
            assertEquals("01-01-1988", response.getDob());
        } catch (Exception ex) {
            System.out.println("Exception in message; ${exception.message}" + ex.getMessage());
        }

    }
}
