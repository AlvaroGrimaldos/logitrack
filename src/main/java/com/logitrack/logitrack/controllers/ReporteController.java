package com.logitrack.logitrack.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.logitrack.logitrack.controllers.AuditoriaController.ErrorResponse;
import com.logitrack.logitrack.controllers.MovimientoController.DetalleRequest;
import com.logitrack.logitrack.entities.AuditoriaCambios;
import com.logitrack.logitrack.entities.MovimientoInventario;
import com.logitrack.logitrack.enums.TipoMovimiento;
import com.logitrack.logitrack.services.MovimientoService;
import com.logitrack.logitrack.services.ReporteService;

@RestController
@RequestMapping("/api/reportes")
@CrossOrigin(origins = "*")
public class ReporteController {
    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    public static class MovimientoRequest {
        private List<DetalleRequest> detalles;
        private String observaciones;

        // Getters y Setters
        public List<DetalleRequest> getDetalles() { return detalles; }
        public void setDetalles(List<DetalleRequest> detalles) { this.detalles = detalles; }
        public String getObservaciones() { return observaciones; }
        public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
    }

    @GetMapping("/movimientos")
    public ResponseEntity<List<MovimientoInventario>>findAll() {
        return ResponseEntity.ok(reporteService.findAll());
    }

    @GetMapping("movimientos/por-bodega/{bodegaId}")
    public ResponseEntity<?> obtenerMovimientosPorBodega(@PathVariable Long bodegaId) {
        try {
            List<MovimientoInventario> movimientos = reporteService.obtenerMovimientosPorBodega(bodegaId);
            return ResponseEntity.ok(movimientos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Error al consultar movimientos: " + e.getMessage()));
        }
    }

    @GetMapping("/movimientos/por-tipo/{tipo}")
    public ResponseEntity<?> obtenerMovimientosPorTipo(@PathVariable TipoMovimiento tipo) {
        try {
            List<MovimientoInventario> movimientos = reporteService.obtenerMovimientosPorTipo(tipo);
            return ResponseEntity.ok(movimientos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Error al consultar movimientos: " + e.getMessage()));
        }
    }

    @GetMapping("/auditoria")
    public ResponseEntity<List<AuditoriaCambios>> obtenerTodasAuditorias() {
        try {
            List<AuditoriaCambios> auditorias = reporteService.obtenerTodasAuditorias();
            return ResponseEntity.ok(auditorias);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/por-entidad/{entidad}")
    public ResponseEntity<?> obtenerAuditoriasPorEntidad(@PathVariable String entidad) {
        try {
            List<AuditoriaCambios> auditorias = reporteService.obtenerAuditoriasPorEntidad(entidad);
            return ResponseEntity.ok(auditorias);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Error al consultar auditorías: " + e.getMessage()));
        }
    }

    @GetMapping("/por-fecha")
    public ResponseEntity<?> obtenerAuditoriasPorFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin) {
        try {
            List<AuditoriaCambios> auditorias = reporteService.obtenerAuditoriasPorFecha(fechaInicio, fechaFin);
            return ResponseEntity.ok(auditorias);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Error al consultar auditorías: " + e.getMessage()));
        }
    }
}
