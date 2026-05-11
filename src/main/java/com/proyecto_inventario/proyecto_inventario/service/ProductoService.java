package com.proyecto_inventario.proyecto_inventario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto_inventario.proyecto_inventario.DTO.ProductoDTO;
import com.proyecto_inventario.proyecto_inventario.DTO.UbicacionDTO;
import com.proyecto_inventario.proyecto_inventario.model.Producto;
import com.proyecto_inventario.proyecto_inventario.repository.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    public Producto obtenerPorId(Integer id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
    }

    private ProductoDTO convertirADTO(Producto entidad) {
        ProductoDTO dto = new ProductoDTO();
        dto.setId(entidad.getId());
        dto.setNombre(entidad.getNombre());
        dto.setSku(entidad.getSku());
            if (entidad.getUbicacion() != null) {
                UbicacionDTO uDto = new UbicacionDTO();
                uDto.setId(entidad.getUbicacion().getId());
                uDto.setPasillo(entidad.getUbicacion().getPasillo());
                uDto.setEstante(entidad.getUbicacion().getEstante());
                dto.setUbicacion(uDto);
            }
        return dto;
    }

    public Producto crear(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto actualizar(Integer id, Producto producto) {
        Producto existente = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
        existente.setNombre(producto.getNombre());
        existente.setSku(producto.getSku());
        existente.setStockActual(producto.getStockActual());
        existente.setUbicacion(producto.getUbicacion());
        return productoRepository.save(existente);
    }

    public void eliminar(Integer id) {
        if (!productoRepository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado con id: " + id);
        }
        productoRepository.deleteById(id);
    }
}