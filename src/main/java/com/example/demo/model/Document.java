package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column
    String orderSubject;
    @Column
    String author;
    @OneToMany
    @JoinTable(name = "executors",
            joinColumns = {@JoinColumn(name = "document_id")},
            inverseJoinColumns = {@JoinColumn(name = "executor_id")}
    )
    List<Employee> executors;
    @Column
    LocalDate expirationDate;
    @Column
    Boolean isControlled;
    @Column
    Boolean isComplited;
    @Column
    String description;
}
