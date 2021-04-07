package com.hgarcia.pruebabackend.service;

import java.util.Optional;

import com.hgarcia.pruebabackend.entity.Pedido;

public interface PedidoService {

    public Iterable<Pedido> findAll();

    public Optional<Pedido> findById(Integer id);

    public Pedido save(Pedido pedido);
    
}