package com.logitrack.logitrack.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logitrack.logitrack.entities.AuditoriaCambios;
import com.logitrack.logitrack.entities.MovimientoInventario;
import com.logitrack.logitrack.enums.TipoMovimiento;
import com.logitrack.logitrack.repositories.AuditoriaCambiosRepository;
import com.logitrack.logitrack.repositories.InventarioRepository;
import com.logitrack.logitrack.repositories.MovimientoDetalleRepository;
import com.logitrack.logitrack.repositories.MovimientoInventarioRepository;
import com.logitrack.logitrack.repositories.ProductoRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReporteService {
    private final MovimientoInventarioRepository movimientoRepository;
    private final MovimientoDetalleRepository movimientoDetalleRepository;
    private final InventarioRepository inventarioRepository;
    private final ProductoRepository productoRepository;
    private final AuthService authService;
    private final AuditoriaService auditoriaService;

    private final AuditoriaCambiosRepository auditoriaRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    public List<MovimientoInventario> findAll() {
        return movimientoRepository.findAll();
    }

    @Transactional
    public List<MovimientoInventario> obtenerMovimientosPorBodega(Long bodegaId) {
        return movimientoRepository.findByBodegaId(bodegaId);
    }

    @Transactional
    public List<MovimientoInventario> obtenerMovimientosPorFecha(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return movimientoRepository.findByFechaBetween(fechaInicio, fechaFin);
    }

    public List<MovimientoInventario> obtenerMovimientosPorTipo(TipoMovimiento tipo) {
        return movimientoRepository.findByTipo(tipo);
    }

    @Transactional
    public List<AuditoriaCambios> obtenerTodasAuditorias() {
        return auditoriaRepository.findAll();
    }

    @Transactional
    public List<AuditoriaCambios> obtenerAuditoriasPorEntidad(String entidad) {
        return auditoriaRepository.findByEntidadAfectada(entidad);
    }

    public List<AuditoriaCambios> obtenerAuditoriasPorFecha(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return auditoriaRepository.findByFechaHoraBetween(fechaInicio, fechaFin);
    }


}
