package com.example.employee.service;

import com.example.employee.model.Employee;
import com.example.employee.model.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDto> getAllEmployees();

    EmployeeDto getEmployee(Long id);

    void save(Employee employee);

    void delete(Employee employee);
}
