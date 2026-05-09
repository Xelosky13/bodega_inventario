package com.proyecto_inventario.proyecto_inventario.service;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto_inventario.proyecto_inventario.model.OrdenRecepcion;
import com.proyecto_inventario.proyecto_inventario.repository.OrdenRecepcionRepository;

@Service
public class OrdenRecepcionService {
    @Autowired
    private OrdenRecepcionRepository orden;

    public OrdenRecepcion buscarOrdenPorId(Integer id) {
        return orden.findById(id).orElse(null);

    }

    public OrdenRecepcion buscarPorProveedor(Integer Id) {
        return orden.findByProveedor(Id);

    }

    public OrdenRecepcion buscarPorFechaRecepción(LocalDate fecha) {
        return orden.findByfechaRecepcion(fecha);
    }

    public void EliminarOrden(Integer id) {
        orden.deleteById(id);
    }

    public void actualizarOrden(Integer id, OrdenRecepcion ordenRec) {
        OrdenRecepcion ordenRecep = orden.findById(id).orElse(null);
        if (ordenRecep != null) {
            ordenRecep.setDetalles(ordenRec.getDetalles());
            ordenRecep.setProveedor(ordenRec.getProveedor());

            orden.save(ordenRecep);
        }

    }

}
