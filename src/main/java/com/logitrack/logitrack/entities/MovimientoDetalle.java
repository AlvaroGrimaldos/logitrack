package com.logitrack.logitrack.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "movimiento_detalle")
public class MovimientoDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "movimiento_id", nullable = false)
    @JsonBackReference
    private MovimientoInventario movimiento;
    
    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;
    
    @Column(nullable = false)
    private Integer cantidad;
    
    // Constructores
    public MovimientoDetalle() {}
    
    public MovimientoDetalle(Producto producto, Integer cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public MovimientoInventario getMovimiento() { return movimiento; }
    public void setMovimiento(MovimientoInventario movimiento) { this.movimiento = movimiento; }
    
    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }
    
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
}