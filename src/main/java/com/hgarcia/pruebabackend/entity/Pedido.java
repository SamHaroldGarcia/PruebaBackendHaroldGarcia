package com.hgarcia.pruebabackend.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "pedidos")
public class Pedido {
    
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String observacion;
		
	private String estado;

	@Column(name = "fecha_creacion")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCreacion;


    @JsonIgnoreProperties(value={"pedidos", "hibernateLazyInitializer", "handler"}, allowSetters=true)
	@ManyToOne(fetch = FetchType.LAZY)
	private Cliente cliente;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "pedido_id")
	private List<PedidoDetalle> detalles;


    public Pedido() {
        this.detalles = new ArrayList<>();
    }


    // Generacion de la fecha de registro del pedido
	@PrePersist
	public void prePersist() {
		this.fechaCreacion = new Date();
	}


    public Double getSubTotal(){
        Double subTotal = 0.00;
        
        for (PedidoDetalle pedidoDetalle : detalles) {
            subTotal += pedidoDetalle.getTotal();
        }

        return subTotal;
    }

    public Double getIva(){
        Double subTotal = this.getSubTotal();
        Double iva = 0.00;

        if(subTotal > 70000){
            iva = subTotal * 0.19;
        }

        return iva;
    }

    public Double getDomicilio(){
        Double domicilio = 0.00;
        Double subTotal = this.getSubTotal();

        if(subTotal > 70000 && subTotal < 100000){
            domicilio = 5000.00;
        }else if(subTotal > 100000){
            domicilio = 0.00;
        }

        return domicilio;
    }

    public Double getTotal(){
        
        Double auxTotal = this.getDomicilio() + this.getIva() + this.getSubTotal();
        Double total = 0.00;

        if(this.estado.equals("CanceladoConCobro")){
            total = auxTotal * 0.10;
        }else if(this.estado.equals("CanceladoSinCobro")){
            total = 0.00;
        }else{
            total = auxTotal;
        }

        return total;
    }
    
    //Getters y Setters

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObservacion() {
        return this.observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<PedidoDetalle> getDetalles() {
        return this.detalles;
    }

    public void setDetalles(List<PedidoDetalle> detalles) {
        this.detalles = detalles;
    }

    
}