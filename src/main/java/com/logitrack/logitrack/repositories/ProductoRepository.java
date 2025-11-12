package com.logitrack.logitrack.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.logitrack.logitrack.entities.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{

    List<Producto> findByNombre(String nombre);

    List<Producto> findByPrecioGreaterThan(BigDecimal precio);

    List<Producto> findByCategoria(String categoria);
}
