package com.example.employee.feign;

import com.example.employee.model.Department;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name ="department" , url ="http://localhost:8080/api/department")
public interface FeignUtil {

    @GetMapping("/get-department/{id}")
    Department getDepartment(@PathVariable("id") Long id);
}
