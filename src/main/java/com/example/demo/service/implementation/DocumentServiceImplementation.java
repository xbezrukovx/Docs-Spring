package com.example.demo.service.implementation;

import com.example.demo.dto.DocumentDTO;
import com.example.demo.model.Document;
import com.example.demo.model.Employee;
import com.example.demo.repository.DocumentRepository;
import com.example.demo.service.DocumentService;
import com.example.demo.service.WorkerService;
import com.example.demo.statemachine.state.DocumentAction;
import com.example.demo.statemachine.state.DocumentState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DocumentServiceImplementation implements DocumentService {
    @Autowired
    StateMachineFactory<DocumentState, DocumentAction> machineFactory;
    @Autowired
    StateMachinePersister<DocumentState, DocumentAction, String> persister;
    @Autowired
    DocumentRepository docRepo;
    @Autowired
    WorkerService workerService;

    public void create(DocumentDTO create) {
        List<Employee> employees = workerService.getWorkersByID(create.getExecutorsId());
        Document document = Document.builder()
                .author(create.getAuthor())
                .expirationDate(create.getExpirationDate())
                .description(create.getDescription())
                .isControlled(create.getIsControlled())
                .orderSubject(create.getOrderSubject())
                .isComplited(false)
                .build();
        document = docRepo.save(document);

        final StateMachine<DocumentState, DocumentAction> stateMachine = machineFactory.getStateMachine();
        stateMachine.sendEvent(DocumentAction.REGISTRATION);
        try {
            persister.persist(stateMachine, document.getId().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("Document was created and registered by " + document.getAuthor());
    }

    public Iterable<Document> getAllDocuments() {
        return docRepo.findAll();
    }

    public List<Document> getAllDocumentsByAuthor(String author) {
        return docRepo.findByAuthor(author);
    }

    public Document getDocumentById(Long id) {
        Optional<Document> optionalDocument = docRepo.findById(id);
        final StateMachine<DocumentState, DocumentAction> stateMachine = machineFactory.getStateMachine();
        try {
            persister.restore(stateMachine, id.toString());
        } catch (Exception e)  {
            e.printStackTrace();
        }
        var state = stateMachine.getState();
        System.out.println(state.getId());

        return optionalDocument.orElse(null);
    }

    public List<Document> getDocumentsByExecutor(Employee employee) {
        return docRepo.findByExecutorsContaining(employee.getId());
    }
}
