package com.example.demo.service.implementation;

import com.example.demo.model.Employee;
import com.example.demo.repository.WorkerRepository;
import com.example.demo.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkerServiceImplementation implements WorkerService {
    @Autowired
    WorkerRepository workerRepository;

    public List<Employee> getWorkersByID(List<Long> workersId) {
        Iterable<Employee> sourceWorkers =  workerRepository.findAllById(workersId);
        List<Employee> targetEmployees = new ArrayList<>();
        sourceWorkers.forEach(targetEmployees::add);
        return targetEmployees;
    }

}
