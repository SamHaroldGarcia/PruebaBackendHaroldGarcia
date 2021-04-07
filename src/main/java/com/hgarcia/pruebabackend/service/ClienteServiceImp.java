package com.hgarcia.pruebabackend.service;

import java.util.Optional;

import com.hgarcia.pruebabackend.dao.ClienteDao;
import com.hgarcia.pruebabackend.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImp implements ClienteService {

    @Autowired
    private ClienteDao clienteDao;

    @Override
    public Iterable<Cliente> findAll() {
        return clienteDao.findAll();
    }

    @Override
    public Optional<Cliente> findById(Integer id) {
        return clienteDao.findById(id);
    }

    @Override
    public Cliente save(Cliente cliente) {
        return clienteDao.save(cliente);
    }

    @Override
    public void deleteById(Integer id) {
        clienteDao.deleteById(id);
    }

}