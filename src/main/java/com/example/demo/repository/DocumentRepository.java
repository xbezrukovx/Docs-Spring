package com.example.demo.repository;

import com.example.demo.model.Document;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends CrudRepository<Document, Long> {
    List<Document> findByAuthor(String author);
    List<Document> findByExecutorsContaining(Long id);
}
