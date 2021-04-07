package com.hgarcia.pruebabackend.service;

import java.util.Optional;

import com.hgarcia.pruebabackend.dao.PedidoDao;
import com.hgarcia.pruebabackend.entity.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PedidoServiceImp implements PedidoService{

    @Autowired
    private PedidoDao pedidoDao;

    @Override
    public Iterable<Pedido> findAll() {
        return pedidoDao.findAll();
    }

    @Override
    public Optional<Pedido> findById(Integer id) {        
        return pedidoDao.findById(id);
    }

    @Override
    public Pedido save(Pedido pedido) {
        return pedidoDao.save(pedido);
    }
    
}