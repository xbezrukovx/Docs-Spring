package com.example.demo.service;

import com.example.demo.model.Employee;

import java.util.List;

public interface WorkerService {
    List<Employee> getWorkersByID(List<Long> workersId);
}
