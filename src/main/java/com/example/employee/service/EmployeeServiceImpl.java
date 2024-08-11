package com.example.employee.service;

import com.example.employee.feign.FeignUtil;
import com.example.employee.model.Department;
import com.example.employee.model.Employee;
import com.example.employee.model.dto.EmployeeDto;
import com.example.employee.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired(required = false)
    FeignUtil feignUtil;

    ModelMapper modelMapper=new ModelMapper();

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employeeList= employeeRepository.findAll();
        List<EmployeeDto> dtos=new ArrayList<>();
        for(Employee employee:employeeList){
            dtos.add(convertToEntity(employee));
        }
        return dtos;
    }

    @Override
    public EmployeeDto getEmployee(Long id) {
        Optional<Employee> employee=employeeRepository.findById(id);
        return employee.map(this::convertToEntity).orElse(null);
    }

    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void delete(Employee employee) {
        employeeRepository.delete(employee);
    }

    private EmployeeDto convertToEntity(Employee employee){
        Department department=feignUtil.getDepartment(employee.getDepartmanId());
        EmployeeDto dto= modelMapper.map(employee,EmployeeDto.class);
        dto.setDepartmanId(department);
        return dto;
    }
}
