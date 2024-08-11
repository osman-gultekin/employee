package com.example.employee.service;

import com.example.employee.feign.FeignUtil;
import com.example.employee.model.Department;
import com.example.employee.model.Employee;
import com.example.employee.model.dto.EmployeeDto;
import com.example.employee.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private FeignUtil feignUtil;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private ModelMapper modelMapper = new ModelMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllEmployees() {
        Employee employee1 = new Employee();
        Employee employee2 = new Employee();
        List<Employee> employeeList = Arrays.asList(employee1, employee2);
        when(employeeRepository.findAll()).thenReturn(employeeList);

        Department department = new Department();
        when(feignUtil.getDepartment(anyLong())).thenReturn(department);

        EmployeeDto employeeDto1 = new EmployeeDto();
        EmployeeDto employeeDto2 = new EmployeeDto();
        when(modelMapper.map(employee1, EmployeeDto.class)).thenReturn(employeeDto1);
        when(modelMapper.map(employee2, EmployeeDto.class)).thenReturn(employeeDto2);

        List<EmployeeDto> dtos = employeeService.getAllEmployees();

        assertEquals(2, dtos.size());
        verify(employeeRepository, times(1)).findAll();
        verify(feignUtil, times(2)).getDepartment(anyLong());
    }

    @Test
    void getEmployee_whenExists() {
        Employee employee = new Employee();
        Department department = new Department();
        EmployeeDto employeeDto = new EmployeeDto();

        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(employee));
        when(feignUtil.getDepartment(anyLong())).thenReturn(department);
        when(modelMapper.map(employee, EmployeeDto.class)).thenReturn(employeeDto);

        EmployeeDto result = employeeService.getEmployee(1L);

        assertEquals(employeeDto, result);
        verify(employeeRepository, times(1)).findById(anyLong());
        verify(feignUtil, times(1)).getDepartment(anyLong());
    }

    @Test
    void getEmployee_whenNotExists() {
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());

        EmployeeDto result = employeeService.getEmployee(1L);

        assertEquals(null, result);
        verify(employeeRepository, times(1)).findById(anyLong());
        verify(feignUtil, times(0)).getDepartment(anyLong());
    }

    @Test
    void save() {
        Employee employee = new Employee();

        employeeService.save(employee);

        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    void delete() {
        Employee employee = new Employee();

        employeeService.delete(employee);

        verify(employeeRepository, times(1)).delete(employee);
    }
}
