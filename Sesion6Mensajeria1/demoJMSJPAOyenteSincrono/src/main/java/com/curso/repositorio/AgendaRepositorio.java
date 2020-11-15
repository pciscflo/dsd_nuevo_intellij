package com.curso.repositorio;


import org.springframework.data.repository.CrudRepository;

import com.curso.entidades.Auto;

public interface AgendaRepositorio extends CrudRepository<Auto, Long> {

}
