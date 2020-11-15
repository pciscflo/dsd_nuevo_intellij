package com.curso.rest;

import javax.validation.Valid;

import com.curso.entidades.Auto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.jms.JmsProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api")
public class JmsRest {

    @Autowired
    private JmsProducer jmsProducer;

    private static final Logger logger = LoggerFactory.getLogger(JmsRest.class);

    @PostMapping("/auto")
    public Auto crearProducto(@Valid @RequestBody Auto auto) {
        ObjectMapper mapper = new ObjectMapper();
        //Object to JSON in String
        String jsonInString = null;
        try {
            jsonInString = mapper.writeValueAsString(auto);
            jmsProducer.send(jsonInString);
            System.out.println("COMERCIO: Enviando " + jsonInString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return auto;
    }
}