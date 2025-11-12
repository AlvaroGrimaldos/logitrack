package com.logitrack.logitrack.entities;

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
    private Integer capacidad;

    // Usamos un campo Long para almacenar el id del usuario encargado.
    // Cuando la clase `Usuario` exista en el proyecto, puedes cambiarlo a:
    //
    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "encargado_id", nullable = false)
    // @JsonBackReference
    // private Usuario encargado;
    //
    @Column(name = "encargado_id")
    private Long encargadoId;

    private boolean activo;

}
