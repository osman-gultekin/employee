package com.example.employee.model.dto;

import lombok.Data;

@Data
public class SaveEmployeeDto {
    private String name;

    private String position;

    private Long salary;

    private Long departmanId;
}
