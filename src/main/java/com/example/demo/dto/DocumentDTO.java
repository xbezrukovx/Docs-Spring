package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class DocumentDTO {
    String orderSubject;
    String author;
    List<Long> executorsId;
    LocalDate expirationDate;
    Boolean isControlled;
    Boolean isComplited;
    String description;
}
