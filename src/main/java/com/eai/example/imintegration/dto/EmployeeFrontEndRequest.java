package com.eai.example.imintegration.dto;

import lombok.*;

@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeFrontEndRequest implements java.io.Serializable {
    private Integer id;
    private String name;
    private Integer age;
}
