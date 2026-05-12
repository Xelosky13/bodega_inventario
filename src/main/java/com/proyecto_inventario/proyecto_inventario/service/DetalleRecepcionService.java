package com.proyecto_inventario.proyecto_inventario.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto_inventario.proyecto_inventario.DTO.DetalleRecepcionDTO;
import com.proyecto_inventario.proyecto_inventario.model.DetalleRecepcion;
import com.proyecto_inventario.proyecto_inventario.repository.DetalleRecepcionRepository;

@Service
public class DetalleRecepcionService {
    @Autowired
    private DetalleRecepcionRepository repository;

    public DetalleRecepcionDTO buscarPorId(Integer id) {
        DetalleRecepcion response = repository.findDetalleRecepcionById(id);
        return this.mappearADTO(response);

    }

    public DetalleRecepcionDTO mappearADTO(DetalleRecepcion detalle) {
        DetalleRecepcionDTO dto = new DetalleRecepcionDTO();
        dto.setCantidad(detalle.getCantidad());
        dto.setEstado(detalle.getEstado());
        dto.setOrden(detalle.getOrden());
        dto.setProducto(detalle.getProducto());
        return dto;

    }

    public DetalleRecepcion mappearAMODELO(DetalleRecepcion detalle) {
        DetalleRecepcion dto = new DetalleRecepcion();
        dto.setCantidad(detalle.getCantidad());
        dto.setEstado(detalle.getEstado());
        dto.setOrden(detalle.getOrden());
        dto.setProducto(detalle.getProducto());
        return dto;

    }

    public List<DetalleRecepcionDTO> buscarPorEstado(String estado) {
        List<DetalleRecepcion> listDetalle = repository.findByEstado(estado);
        return listDetalle.stream().map(this::mappearADTO).toList();

    }

    /*
     * public DetalleRecepcionDTO buscarPorProducto(Producto producto) {
     * DetalleRecepcion detalle=repository.findByProducto(producto);
     * }
     */

    public void deleteById(Integer id) {
        repository.deleteById(id);

    }

    public DetalleRecepcionDTO guardarDetalleRecepcion(DetalleRecepcionDTO dto) {
        DetalleRecepcion detalleRecepcion = new DetalleRecepcion();
        detalleRecepcion.setCantidad(dto.getCantidad());
        detalleRecepcion.setEstado(dto.getEstado());
        detalleRecepcion.setOrden(dto.getOrden());
        detalleRecepcion.setProducto(dto.getProducto());

        DetalleRecepcion guardado = repository.save(detalleRecepcion);

        return this.mappearADTO(guardado);
    }

    public void actualizarDetalleRecepcion(Integer id, DetalleRecepcionDTO detalle) {
        DetalleRecepcion detalleRec = repository.findById(id).orElse(null);

        if (detalleRec != null) {
            detalleRec.setCantidad(detalle.getCantidad());
            detalleRec.setEstado(detalle.getEstado());
            detalleRec.setOrden(detalle.getOrden());
            detalleRec.setProducto(detalle.getProducto());
            repository.save(detalleRec);

        }

    }

}
