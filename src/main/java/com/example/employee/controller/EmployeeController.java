package com.example.employee.controller;

import com.example.employee.feign.FeignUtil;
import com.example.employee.model.Employee;
import com.example.employee.model.dto.EmployeeDto;
import com.example.employee.model.dto.SaveEmployeeDto;
import com.example.employee.service.EmployeeServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeService;

    private ModelMapper modelMapper=new ModelMapper();

    @GetMapping("/get-all-employees")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<EmployeeDto> employeeList = employeeService.getAllEmployees();
        return ResponseEntity.ok(employeeList);
    }

    @GetMapping("/get-employee/{id}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable Long id) {
        EmployeeDto employee = employeeService.getEmployee(id);
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        EmployeeDto deletedEmployee = employeeService.getEmployee(id);
        if (!Objects.isNull(deletedEmployee)) {
            employeeService.delete(modelMapper.map(deletedEmployee,Employee.class));
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/save")
    public void saveDepartment(@RequestBody SaveEmployeeDto employeeDto){
        employeeService.save(modelMapper.map(employeeDto,Employee.class));
    }

    @PostMapping("/update")
    public void updateDepartment(@RequestBody Employee employee){
        employeeService.save(employee);
    }
}

