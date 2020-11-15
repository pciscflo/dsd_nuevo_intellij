package com.upc.curso.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.upc.curso.jms.JmsProducerConsumer;

@RestController
@RequestMapping("/api")
public class JmsRest {
    @Autowired
    private JmsProducerConsumer jmsProducer;

    @PostMapping("/enviar/{mensaje}")
    public String enviar(@PathVariable(value = "mensaje") String mensaje) {
        jmsProducer.enviarRecibir(mensaje);

        return "Enviado!";
    }

}
