package com.hgarcia.pruebabackend.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.hgarcia.pruebabackend.entity.Cliente;
import com.hgarcia.pruebabackend.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Cliente cliente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(cliente));
    }

    @GetMapping
    public List<Cliente> getAll() {
        return StreamSupport.stream(clienteService.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(value = "id") Integer clienteId) {
        Optional<Cliente> optCliente = clienteService.findById(clienteId);

        if (!optCliente.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(optCliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Cliente clienteUpt, @PathVariable(value = "id") Integer clienteId) {
        Optional<Cliente> optCliente = clienteService.findById(clienteId);

        if (!optCliente.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        optCliente.get().setDni(clienteUpt.getDni());
        optCliente.get().setNombre(clienteUpt.getNombre());
        optCliente.get().setDireccion(clienteUpt.getDireccion());

        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(optCliente.get()));

    }

}