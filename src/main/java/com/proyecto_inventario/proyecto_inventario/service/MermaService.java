package com.proyecto_inventario.proyecto_inventario.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto_inventario.proyecto_inventario.DTO.MermaDTO;
import com.proyecto_inventario.proyecto_inventario.model.Merma;
import com.proyecto_inventario.proyecto_inventario.model.Producto;
import com.proyecto_inventario.proyecto_inventario.repository.MermaRepository;
import com.proyecto_inventario.proyecto_inventario.repository.ProductoRepository;

@Service
public class MermaService {

    @Autowired
    private MermaRepository mermaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public List<MermaDTO> listarTodas() {
        return mermaRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public MermaDTO obtenerPorId(Integer id) {
        Merma merma = mermaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Merma no encontrada con id: " + id));
        return convertirADTO(merma);
    }

    public MermaDTO registrar(MermaDTO dto) {
        Producto producto = productoRepository.findById(dto.getProductoId())
                .orElseThrow(() -> new RuntimeException(
                        "Producto no encontrado con id: " + dto.getProductoId()));

        if (producto.getStockActual() < dto.getCantidad()) {
            throw new RuntimeException("Stock insuficiente. Stock actual: "
                    + producto.getStockActual());
        }

        producto.setStockActual(producto.getStockActual() - dto.getCantidad());
        productoRepository.save(producto);

        Merma merma = new Merma();
        merma.setProducto(producto);
        merma.setCantidad(dto.getCantidad());
        merma.setMotivo(dto.getMotivo());
        merma.setFechaReporte(dto.getFechaReporte());

        return convertirADTO(mermaRepository.save(merma));
    }

    public void eliminar(Integer id) {
        if (!mermaRepository.existsById(id)) {
            throw new RuntimeException("Merma no encontrada con id: " + id);
        }
        mermaRepository.deleteById(id);
    }

    private MermaDTO convertirADTO(Merma merma) {
        MermaDTO dto = new MermaDTO();
        dto.setId(merma.getId());
        dto.setProductoId(merma.getProducto().getId());
        dto.setNombreProducto(merma.getProducto().getNombre());
        dto.setCantidad(merma.getCantidad());
        dto.setMotivo(merma.getMotivo());
        dto.setFechaReporte(merma.getFechaReporte());
        return dto;
    }
}