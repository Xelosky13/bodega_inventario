package com.proyecto_inventario.proyecto_inventario.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto_inventario.proyecto_inventario.model.DetalleRecepcion;
import com.proyecto_inventario.proyecto_inventario.model.Producto;
import com.proyecto_inventario.proyecto_inventario.repository.DetalleRecepcionRepository;

@Service
public class DetalleRecepcionService {
    @Autowired
    private DetalleRecepcionRepository repository;

    public DetalleRecepcion buscarPorId(Integer id) {
        return repository.findDetalleRecepcionById(id);
    }

    public DetalleRecepcion buscarPorEstado(String estado) {
        return repository.findByEstado(estado);

    }

    public DetalleRecepcion buscarPorCantidad(Integer id) {
        return repository.findByCantidad(id);

    }

    public DetalleRecepcion buscarPorOrden(Integer id) {
        return repository.findbyOrden(id);
    }

    public DetalleRecepcion buscarPorProducto(Producto producto) {
        return repository.findByProducto(producto);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);

    }

    public ArrayList<DetalleRecepcion> buscarDetallePorEstado(String estado) {
        return repository.buscarDetallePorEstado(estado);
    }

    public ArrayList<DetalleRecepcion> buscarDetallePorCantidad(Integer cantidad) {
        return repository.buscarDetallePorCantidad(cantidad);
    }

    public void actualizarDetalleRecepcion(Integer id, DetalleRecepcion detalle) {
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
