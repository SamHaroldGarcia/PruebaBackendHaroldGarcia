package com.hgarcia.pruebabackend.service;

import java.util.Optional;

import com.hgarcia.pruebabackend.entity.Producto;

public interface ProductoService {

    public Iterable<Producto> findAll();

    public Optional<Producto> findById(Integer id);

    public Producto save(Producto cliente);

    public void deleteById(Integer id);
    
}