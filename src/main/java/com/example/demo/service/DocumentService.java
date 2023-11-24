package com.example.demo.service;

import com.example.demo.dto.DocumentDTO;
import com.example.demo.model.Document;
import com.example.demo.model.Employee;

import java.util.List;

public interface DocumentService {
    void create(DocumentDTO create);
    Iterable<Document> getAllDocuments();
    List<Document> getAllDocumentsByAuthor(String author);
    Document getDocumentById(Long id);
    List<Document> getDocumentsByExecutor(Employee employee);
}
