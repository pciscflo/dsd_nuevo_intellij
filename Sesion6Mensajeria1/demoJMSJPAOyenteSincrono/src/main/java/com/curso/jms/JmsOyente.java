package com.curso.jms;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.curso.entidades.Auto;
import com.curso.repositorio.AgendaRepositorio;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JmsOyente {
    @Autowired
    private AgendaRepositorio agendaRepositorio;

    @JmsListener(destination = "${jms.queue.destination}")
    public void miMensaje(String mensajeJson) {
        System.out.println("Recibido:" + mensajeJson);
        //mensajeJSON a Objeto Agenda
        ObjectMapper mapper = new ObjectMapper();
        try {
            Auto auto = mapper.readValue(mensajeJson, Auto.class);
            System.out.println(mensajeJson);
            auto.setDrespuesta("Registrado");
            agendaRepositorio.save(auto);//registra en la base de  datos
        } catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}