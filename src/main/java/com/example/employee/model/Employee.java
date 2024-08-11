package com.example.employee.model;

import jakarta.persistence.*;
import lombok.Data;



@Data
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_id_seq1")
    @SequenceGenerator(name = "employee_id_seq1", sequenceName = "employee_id_seq", allocationSize = 1)
    private Long id;

    @Column
    private String name;

    @Column
    private String position;

    @Column
    private Long salary;

    @Column
    private Long departmanId;
}
