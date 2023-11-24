package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column
    String firstName;
    @Column
    String lastName;
    @Column
    String middleName;
    @Column
    String workPosition;
}
