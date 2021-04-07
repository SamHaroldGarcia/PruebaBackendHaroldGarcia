package com.hgarcia.pruebabackend.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.hgarcia.pruebabackend.entity.Pedido;
import com.hgarcia.pruebabackend.entity.PedidoDetalle;
import com.hgarcia.pruebabackend.service.PedidoService;
import com.hgarcia.pruebabackend.util.TimeUtil;
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
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    //Instancia del metodo que permite el calculo de horas entre la fecha del pedido y la fecha actual
    private TimeUtil timeUtil = new TimeUtil();

    //Registrar un nuevo pedido
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Pedido pedido) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.save(pedido));
    }
    //Traer el listado de todos los pedidos
    @GetMapping
    public List<Pedido> getAll() {
        return StreamSupport.stream(pedidoService.findAll().spliterator(), false).collect(Collectors.toList());
    }

    //COnsultar un pedido por su id
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(value = "id") Integer pedidoId) {
        // Consultar pedido en la base de datos
        Optional<Pedido> optPedido = pedidoService.findById(pedidoId);

        // Verificar si el pedido fue encontrado en la base de datos
        if (!optPedido.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(optPedido.get());
        
    }
    //Editar o modificar pedido
    @PutMapping("modificar/{id}")
    public ResponseEntity<?> update(@RequestBody Pedido pedidoModificado,
            @PathVariable(value = "id") Integer pedidoId) {

        // Consultar pedido en la base de datos
        Optional<Pedido> optPedido = pedidoService.findById(pedidoId);

        // Verificar si el pedido fue encontrado en la base de datos
        if (!optPedido.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        // Variable que obtiene en que fecha y hora fue realizado el pedido
        Date fechaPedido = optPedido.get().getFechaCreacion();

        // variable que calcula la diferencia de horas entre la fecha del pedido y la
        // fecha actual
        Long horasPedido = timeUtil.calcularHorasPedido(fechaPedido);

        // Criterio de aceptacion historia 2
        // confirmar si no han pasado mas de 5 horas desde que se realizo el pedido para
        // permitir su actualizacion
        if (horasPedido > 5) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Han pasado mas de 5 horas no se puede actualizar el pedido");
        }

        // Variable que obtiene el valor actual del pedido
        Double valorActual = optPedido.get().getSubTotal();

        // Bloque que calcula el nuevo valor del pedido
        Double nuevoValor = 0.00;

        for (PedidoDetalle detalle : pedidoModificado.getDetalles()) {
            nuevoValor += detalle.getTotal();
        }

        System.out.println("nuevo valor: " + nuevoValor);
        System.out.println("antiguo valor: " + valorActual);

        // Criterio de aceptacion historia 2
        // confirmar o veerificar si el valor actual es mayor o igual que el valor nuevo
        // de lo contrario no se puede actualizar el pedido
        if (nuevoValor < valorActual) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("El nuevo valor del pedido es menor que el anterior, no se puede actualizar el pedido");
        }

        // Actualizacion del pedido que se guardara en base de datos
        optPedido.get().setDetalles(pedidoModificado.getDetalles());
        optPedido.get().setObservacion(pedidoModificado.getObservacion());

        // retorno y guardato de pedido modificado en la base de datos
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.save(optPedido.get()));
    }


    @PutMapping("/cancelar/{id}")
    public ResponseEntity<?> cancel(@PathVariable(value = "id") Integer pedidoId){

         // Consultar pedido en la base de datos
         Optional<Pedido> optPedido = pedidoService.findById(pedidoId);

         // Verificar si el pedido fue encontrado en la base de datos
         if (!optPedido.isPresent()) {
             return ResponseEntity.notFound().build();
         }

         // Variable que obtiene en que fecha y hora fue realizado el pedido
        Date fechaPedido = optPedido.get().getFechaCreacion();

        // variable que calcula la diferencia de horas entre la fecha del pedido y la
        // fecha actual
        Long horasPedido = timeUtil.calcularHorasPedido(fechaPedido);

        // Criterio de aceptacion historia 2
        // confirmar si no han pasado mas de 5 horas desde que se realizo el pedido para
        // permitir su actualizacion
        if (horasPedido < 12) {
            optPedido.get().setEstado("CanceladoSinCobro");
        }else if(horasPedido > 12){
            optPedido.get().setEstado("CanceladoConCobro");
        }

        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.save(optPedido.get()));
    }

}