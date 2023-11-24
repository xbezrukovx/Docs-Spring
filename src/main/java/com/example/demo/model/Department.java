package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column
    String name;
    @Column
    String contacts;
    @Column
    String manager;
}
