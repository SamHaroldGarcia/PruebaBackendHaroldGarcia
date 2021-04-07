package com.hgarcia.pruebabackend.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.hgarcia.pruebabackend.entity.Producto;
import com.hgarcia.pruebabackend.service.ProductoService;
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
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Producto producto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.save(producto));
    }

    @GetMapping
    public List<Producto> getAll() {
       return StreamSupport.stream(productoService.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(value = "id") Integer productoId){
        Optional<Producto> optProducto = productoService.findById(productoId);

        if(!optProducto.isPresent()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(optProducto.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Producto productoUpt, @PathVariable(value = "id") Integer productoId) {
        Optional<Producto> optProducto = productoService.findById(productoId);

        if (!optProducto.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        
        optProducto.get().setNombre(productoUpt.getNombre());
        optProducto.get().setPrecio(productoUpt.getPrecio());

        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.save(optProducto.get()));

    }
}