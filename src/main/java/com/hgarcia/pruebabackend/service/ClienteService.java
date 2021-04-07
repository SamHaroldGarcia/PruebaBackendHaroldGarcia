package com.hgarcia.pruebabackend.service;

import java.util.Optional;

import com.hgarcia.pruebabackend.entity.Cliente;

public interface ClienteService {

    public Iterable<Cliente> findAll();

    public Optional<Cliente> findById(Integer id);

    public Cliente save(Cliente cliente);

    public void deleteById(Integer id);

}