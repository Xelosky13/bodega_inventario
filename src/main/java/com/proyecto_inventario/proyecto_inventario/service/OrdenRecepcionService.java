package com.proyecto_inventario.proyecto_inventario.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto_inventario.proyecto_inventario.DTO.OrdenRecepcionDTO;
import com.proyecto_inventario.proyecto_inventario.model.OrdenRecepcion;
import com.proyecto_inventario.proyecto_inventario.repository.OrdenRecepcionRepository;

@Service
public class OrdenRecepcionService {
    @Autowired
    private OrdenRecepcionRepository repo;

    public OrdenRecepcionDTO buscarOrdenPorId(Integer id) {
        OrdenRecepcion response = repo.findById(id).orElse(null);
        return this.mappearAModelo(response);

    }

    public OrdenRecepcionDTO mappearAModelo(OrdenRecepcion orden) {
        OrdenRecepcionDTO dto = new OrdenRecepcionDTO();
        dto.setDetalles(orden.getDetalles());
        dto.setProvedor(orden.getProveedor());
        dto.setFechaRecepcion(orden.getFechaRecepcion());
        return dto;

    }

    public OrdenRecepcionDTO buscarPorProveedor(Integer id) {
        OrdenRecepcion ordenRecepcion = repo.findByProveedor(id);
        return this.mappearAModelo(ordenRecepcion);
    }

    public OrdenRecepcionDTO buscarPorFechaRecepcion(LocalDate fecha) {
        OrdenRecepcion ordenRecepcion = repo.findByfechaRecepcion(fecha);
        return this.mappearAModelo(ordenRecepcion);
    }

    public void EliminarOrden(Integer id) {
        repo.deleteById(id);
    }

    public void actualizarOrden(Integer id, OrdenRecepcionDTO ordenRec) {
        OrdenRecepcion ordenRecep = repo.findById(id).orElse(null);
        if (ordenRecep != null) {
            ordenRecep.setDetalles(ordenRec.getDetalles());
            ordenRecep.setProveedor(ordenRec.getProvedor());
            repo.save(ordenRecep);
        }

    }

    public OrdenRecepcionDTO guardarOrden(OrdenRecepcionDTO dto) {
        OrdenRecepcion ordenRecepcion = new OrdenRecepcion();
        ordenRecepcion.setDetalles(dto.getDetalles());
        ordenRecepcion.setFechaRecepcion(dto.getFechaRecepcion());
        ordenRecepcion.setProveedor(dto.getProvedor());

        OrdenRecepcion guardado = repo.save(ordenRecepcion);

        return this.mappearAModelo(guardado);
    }

    public List<OrdenRecepcionDTO> listarOrdenes() {
        List<OrdenRecepcion> orden = repo.findAll();
        return orden.stream().map(this::mappearAModelo).toList();

    }

}
