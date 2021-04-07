package com.hgarcia.pruebabackend.dao;

import com.hgarcia.pruebabackend.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoDao extends JpaRepository<Producto, Integer> {
    
}
