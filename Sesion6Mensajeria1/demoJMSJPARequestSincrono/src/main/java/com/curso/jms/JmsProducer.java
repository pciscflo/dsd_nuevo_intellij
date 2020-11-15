package com.curso.jms;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsProducer {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${jms.cola.envio}")
    String destinationQueue;

    public void send(String msg) {
        String id = UUID.randomUUID().toString();
        jmsTemplate.convertAndSend(destinationQueue, msg, m -> {
            m.setJMSCorrelationID(id);
            return m;
        });

    }

}
