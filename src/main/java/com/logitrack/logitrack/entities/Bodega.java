package com.logitrack.logitrack.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name= "bodega")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Bodega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 255)
    private String ubicacion;

    @Column(nullable = true)
    private Integer capacidad;

    // Usamos un campo Long para almacenar el id del usuario encargado.
    // Cuando la clase `Usuario` exista en el proyecto, puedes cambiarlo a:
    //
    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "encargado_id", nullable = false)
    // @JsonBackReference
    // private Usuario encargado;
    //
    @Column(name = "encargado_id", nullable = false)
    private Long encargadoId;

    @Column(nullable = false) 
    private boolean activo;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "update_at", nullable = true)
    @UpdateTimestamp
    private LocalDateTime updateAt;
}
