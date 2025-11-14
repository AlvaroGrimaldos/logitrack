package com.logitrack.logitrack.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.logitrack.logitrack.entities.Bodega;

@Repository
public interface BodegaRepository extends JpaRepository<Bodega, Long>{
    
    List<Bodega> findByEncargadoId(Long encargadoId);

    List<Bodega> findByCapacidadGreaterThan(Integer capacidad);

    @Query("select b from Bodega b where b.encargado.nombre = :nombreEncargado")
    List<Bodega> findByEncargadoNombre(@Param("nombreEncargado") String nombreEncargado);
}
