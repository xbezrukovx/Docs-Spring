package com.example.demo.controller;

import com.example.demo.dto.DocumentDTO;
import com.example.demo.model.Document;
import com.example.demo.service.DocumentService;
import com.example.demo.service.implementation.DocumentServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doc")
@CrossOrigin
public class DocumentController {
    @Autowired
    DocumentService docService;

    @GetMapping("/{id}")
    public Document getDocument(@PathVariable long id) {
        return docService.getDocumentById(id);
    }

    @GetMapping("/")
    public Iterable<Document> getAllDocuments() {
        return docService.getAllDocuments();
    }

    @PostMapping("/")
    public ResponseEntity<Void> createDocument(@RequestBody DocumentDTO document) {
        docService.create(document);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
