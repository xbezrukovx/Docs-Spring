package com.example.demo.statemachine;

import com.example.demo.statemachine.state.DocumentAction;
import com.example.demo.statemachine.state.DocumentState;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;

import java.util.HashMap;

public class InMemoryDocumentStateMachinePersist implements StateMachinePersist<DocumentState, DocumentAction, String> {

    private final HashMap<String, StateMachineContext<DocumentState, DocumentAction>> contexts = new HashMap<>();

    @Override
    public void write(StateMachineContext<DocumentState, DocumentAction> stateMachineContext, String s) throws Exception {
        contexts.put(s, stateMachineContext);
    }

    @Override
    public StateMachineContext<DocumentState, DocumentAction> read(String s) throws Exception {
        return contexts.get(s);
    }
}
