package com.logitrack.logitrack.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.logitrack.logitrack.dto.InventarioDTO;
import com.logitrack.logitrack.entities.Inventario;
import com.logitrack.logitrack.repositories.InventarioRepository;
import com.logitrack.logitrack.repositories.ProductoRepository;
import com.logitrack.logitrack.repositories.BodegaRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventarioService {

    private final InventarioRepository inventarioRepository;
    private final ProductoRepository productoRepository;
    private final BodegaRepository bodegaRepository;

    @Transactional
    public List<InventarioDTO> findAll() {
        return inventarioRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public InventarioDTO findById(Long id) {
        Inventario inventario = inventarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado con id: " + id));
        return convertToDto(inventario);
    }

    @Transactional
    public List<InventarioDTO> findByProductoId(Long productoId) {
        // Validar que el producto existe
        if (!productoRepository.existsById(productoId)) {
            throw new RuntimeException("Producto no encontrado con id: " + productoId);
        }
        return inventarioRepository.findByProductoId(productoId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<InventarioDTO> findByBodegaId(Long bodegaId) {
        // Validar que la bodega existe
        if (!bodegaRepository.existsById(bodegaId)) {
            throw new RuntimeException("Bodega no encontrada con id: " + bodegaId);
        }
        return inventarioRepository.findByBodegaId(bodegaId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public InventarioDTO findByProductoAndBodega(Long productoId, Long bodegaId) {
        Inventario inventario = inventarioRepository.findByProductoIdAndBodegaId(productoId, bodegaId).stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado para producto: " + productoId + " y bodega: " + bodegaId));
        return convertToDto(inventario);
    }

    @Transactional
    public InventarioDTO save(InventarioDTO dto) {
        // Validaciones de negocio
        if (dto.getProductoId() == null) {
            throw new RuntimeException("El id del producto es obligatorio");
        }
        if (dto.getBodegaId() == null) {
            throw new RuntimeException("El id de la bodega es obligatorio");
        }
        if (dto.getStockActual() == null || dto.getStockActual() < 0) {
            throw new RuntimeException("El stock actual no puede ser negativo");
        }

        // Verificar que el producto existe
        if (!productoRepository.existsById(dto.getProductoId())) {
            throw new RuntimeException("Producto no encontrado con id: " + dto.getProductoId());
        }

        // Verificar que la bodega existe
        if (!bodegaRepository.existsById(dto.getBodegaId())) {
            throw new RuntimeException("Bodega no encontrada con id: " + dto.getBodegaId());
        }

        // Verificar que no existe inventario duplicado (mismo producto y bodega)
        if (inventarioRepository.existsByProductoIdAndBodegaId(dto.getProductoId(), dto.getBodegaId())) {
            throw new RuntimeException("Ya existe inventario para el producto: " + dto.getProductoId() + " en la bodega: " + dto.getBodegaId());
        }

        Inventario inventario = new Inventario();
        inventario.setProductoId(dto.getProductoId());
        inventario.setBodegaId(dto.getBodegaId());
        inventario.setStockActual(dto.getStockActual());

        Inventario guardado = inventarioRepository.save(inventario);
        return convertToDto(guardado);
    }

    @Transactional
    public InventarioDTO update(Long id, InventarioDTO dto) {
        Inventario inventario = inventarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado con id: " + id));

        // Validaciones de negocio
        if (dto.getStockActual() == null || dto.getStockActual() < 0) {
            throw new RuntimeException("El stock actual no puede ser negativo");
        }

        // Si cambia producto o bodega, verificar que existen
        if (dto.getProductoId() != null && !productoRepository.existsById(dto.getProductoId())) {
            throw new RuntimeException("Producto no encontrado con id: " + dto.getProductoId());
        }
        if (dto.getBodegaId() != null && !bodegaRepository.existsById(dto.getBodegaId())) {
            throw new RuntimeException("Bodega no encontrada con id: " + dto.getBodegaId());
        }

        // Verificar duplicado si cambia producto o bodega (excluyendo el mismo id)
        Long productoId = dto.getProductoId() != null ? dto.getProductoId() : inventario.getProductoId();
        Long bodegaId = dto.getBodegaId() != null ? dto.getBodegaId() : inventario.getBodegaId();

        inventarioRepository.findByProductoIdAndBodegaIdAndIdNot(productoId, bodegaId, id).stream()
                .findAny()
                .ifPresent(inv -> {
                    throw new RuntimeException("Ya existe inventario para el producto: " + productoId + " en la bodega: " + bodegaId);
                });

        if (dto.getProductoId() != null) {
            inventario.setProductoId(dto.getProductoId());
        }
        if (dto.getBodegaId() != null) {
            inventario.setBodegaId(dto.getBodegaId());
        }
        if (dto.getStockActual() != null) {
            inventario.setStockActual(dto.getStockActual());
        }

        Inventario actualizado = inventarioRepository.save(inventario);
        return convertToDto(actualizado);
    }

    @Transactional
    public void delete(Long id) {
        if (!inventarioRepository.existsById(id)) {
            throw new RuntimeException("Inventario no encontrado con id: " + id);
        }
        inventarioRepository.deleteById(id);
    }

    private InventarioDTO convertToDto(Inventario inventario) {
        InventarioDTO dto = new InventarioDTO();
        dto.setId(inventario.getId());
        dto.setProductoId(inventario.getProductoId());
        dto.setBodegaId(inventario.getBodegaId());
        dto.setStockActual(inventario.getStockActual());
        return dto;
    }
}
