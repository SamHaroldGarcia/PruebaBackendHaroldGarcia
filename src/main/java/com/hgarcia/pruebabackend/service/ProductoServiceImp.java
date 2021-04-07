package com.hgarcia.pruebabackend.service;

import java.util.Optional;

import com.hgarcia.pruebabackend.dao.ProductoDao;
import com.hgarcia.pruebabackend.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImp implements ProductoService{

    @Autowired
    private ProductoDao productoDao;

    @Override
    public Iterable<Producto> findAll() {
        return productoDao.findAll();
    }

    @Override
    public Optional<Producto> findById(Integer id) {
        return productoDao.findById(id);
        }

    @Override
    public Producto save(Producto producto) {
        return productoDao.save(producto);
    }

    @Override
    public void deleteById(Integer id) {
        productoDao.deleteById(id);        
    }


    
}