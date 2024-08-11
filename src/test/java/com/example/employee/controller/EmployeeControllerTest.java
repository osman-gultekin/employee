package com.example.employee.controller;

import com.example.employee.model.Employee;
import com.example.employee.model.dto.EmployeeDto;
import com.example.employee.model.dto.SaveEmployeeDto;
import com.example.employee.service.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class EmployeeControllerTest {

    @Mock
    private EmployeeServiceImpl employeeService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllEmployees() {
        EmployeeDto employeeDto1 = new EmployeeDto();
        EmployeeDto employeeDto2 = new EmployeeDto();
        when(employeeService.getAllEmployees()).thenReturn(Arrays.asList(employeeDto1, employeeDto2));

        ResponseEntity<List<EmployeeDto>> response = employeeController.getAllEmployees();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void getEmployee_whenExists() {
        EmployeeDto employeeDto = new EmployeeDto();
        when(employeeService.getEmployee(anyLong())).thenReturn(employeeDto);

        ResponseEntity<EmployeeDto> response = employeeController.getEmployee(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(employeeDto, response.getBody());
    }

    @Test
    void getEmployee_whenNotExists() {
        when(employeeService.getEmployee(anyLong())).thenReturn(null);

        ResponseEntity<EmployeeDto> response = employeeController.getEmployee(111L);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void delete_whenEmployeeExists() {
        EmployeeDto employeeDto = new EmployeeDto();
        Employee employee = new Employee();
        when(employeeService.getEmployee(anyLong())).thenReturn(employeeDto);
        when(modelMapper.map(employeeDto, Employee.class)).thenReturn(employee);

        ResponseEntity<String> response = employeeController.delete(1L);

        verify(employeeService, times(1)).delete(employee);
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void delete_whenEmployeeNotExists() {
        when(employeeService.getEmployee(anyLong())).thenReturn(null);

        ResponseEntity<String> response = employeeController.delete(1L);

        verify(employeeService, times(0)).delete(any());
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void save() {
        SaveEmployeeDto employeeDto = new SaveEmployeeDto();
        Employee employee = new Employee();
        when(modelMapper.map(employeeDto, Employee.class)).thenReturn(employee);

        employeeController.saveDepartment(employeeDto);

        verify(employeeService, times(1)).save(employee);
    }

    @Test
    void update() {
        Employee employee = new Employee();

        employeeController.updateDepartment(employee);

        verify(employeeService, times(1)).save(employee);
    }
}
