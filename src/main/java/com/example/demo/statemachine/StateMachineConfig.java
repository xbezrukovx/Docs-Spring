package com.example.demo.statemachine;

import com.example.demo.model.Document;
import com.example.demo.statemachine.state.DocumentAction;
import com.example.demo.statemachine.state.DocumentState;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.statemachine.state.State;

import java.util.EnumSet;

@Configuration
@EnableStateMachineFactory
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<DocumentState, DocumentAction> {
    @Override
    public void configure(StateMachineConfigurationConfigurer<DocumentState, DocumentAction> config) throws Exception {
        config.withConfiguration()
                .autoStartup(true)
                .listener(listener());
    }

    @Override
    public void configure(StateMachineStateConfigurer<DocumentState, DocumentAction> states) throws Exception {
        states.withStates()
                .initial(DocumentState.NEW)
                .end(DocumentState.ACCEPT)
                .states(EnumSet.allOf(DocumentState.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<DocumentState, DocumentAction> transitions) throws Exception {
        transitions
                .withExternal()
                .source(DocumentState.NEW)
                .target(DocumentState.PROGRESS)
                .event(DocumentAction.REGISTRATION)

                .and()
                .withExternal()
                .source(DocumentState.PROGRESS)
                .target(DocumentState.CONTROL)
                .event(DocumentAction.COMPLETION)

                .and()
                .withExternal()
                .source(DocumentState.CONTROL)
                .target(DocumentState.ACCEPT)
                .event(DocumentAction.CONFIRMATION)

                .and()
                .withExternal()
                .source(DocumentState.CONTROL)
                .target(DocumentState.REVISION)
                .event(DocumentAction.REJECTION)

                .and()
                .withExternal()
                .source(DocumentState.REVISION)
                .target(DocumentState.PROGRESS)
                .event(DocumentAction.RECOVER)
                ;
    }

    @Bean
    public StateMachineListener<DocumentState, DocumentAction> listener() {
        return new StateMachineListenerAdapter<DocumentState, DocumentAction>() {
            @Override
            public void stateChanged(State<DocumentState, DocumentAction> from, State<DocumentState, DocumentAction> to) {
                System.out.println("State change to " + to.getId());
            }
        };
    }

    @Bean
    public StateMachinePersister<DocumentState, DocumentAction, String> persister() {
        return new DefaultStateMachinePersister<>(new InMemoryDocumentStateMachinePersist());
    }
}
