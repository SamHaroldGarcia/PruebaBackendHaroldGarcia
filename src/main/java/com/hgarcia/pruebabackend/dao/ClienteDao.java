package com.hgarcia.pruebabackend.dao;

import com.hgarcia.pruebabackend.entity.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteDao extends JpaRepository<Cliente, Integer> {

}