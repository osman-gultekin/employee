package com.example.employee.model.dto;

import com.example.employee.model.Department;
import lombok.Data;

@Data
public class EmployeeDto {
    private String name;

    private String position;

    private Long salary;

    private Department departmanId;
}
