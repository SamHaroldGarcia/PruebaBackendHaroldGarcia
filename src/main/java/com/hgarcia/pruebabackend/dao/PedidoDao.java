package com.hgarcia.pruebabackend.dao;

import com.hgarcia.pruebabackend.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoDao extends JpaRepository<Pedido, Integer>{
    
}