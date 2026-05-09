package com.proyecto_inventario.proyecto_inventario.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto_inventario.proyecto_inventario.DTO.ProveedorDTO;
import com.proyecto_inventario.proyecto_inventario.model.OrdenRecepcion;
import com.proyecto_inventario.proyecto_inventario.model.Proveedor;
import com.proyecto_inventario.proyecto_inventario.repository.OrdenRecepcionRepository;
import com.proyecto_inventario.proyecto_inventario.repository.ProveedorRepository;

@Service
public class ProveedorService {
    @Autowired
    private ProveedorRepository proveRepo;
    @Autowired
    private OrdenRecepcionRepository ordenrepo;

    public ProveedorDTO mapearADTO(Proveedor prov) {
        ProveedorDTO dto = new ProveedorDTO();
        dto.setNombre(prov.getNombre());
        dto.setNombreContacto(prov.getNombreContacto());
        dto.setOrdenes(prov.getOrdenes());
        dto.setTelefono(prov.getTelefono());
        dto.setRut(prov.getRut());
        return dto;

    }

    public ProveedorDTO buscarPorId(Integer id) {
        Proveedor prov = proveRepo.findById(id).orElse(null);

        return this.mapearADTO(prov);
    }

    public List<ProveedorDTO> buscarPorNombre(String nombre) {
        List<Proveedor> prov = proveRepo.findByNombre(nombre);

        return prov.stream().map(this::mapearADTO).toList();
    }

    public ProveedorDTO buscarPorRut(String rut) {
        Proveedor prov = proveRepo.findByRut(rut);
        return this.mapearADTO(prov);
    }

    public ProveedorDTO buscarPorOrdenes(Integer id) {
        OrdenRecepcion orden = ordenrepo.findById(id).orElse(null);

        if (orden == null) {
            return null;
        }

        return this.mapearADTO(orden.getProveedor());

    }

    public ProveedorDTO buscarPorTelefono(String telefono) {
        Proveedor prov = proveRepo.findByTelefono(telefono);

        return this.mapearADTO(prov);
    }

    public List<ProveedorDTO> Proveedores() {
        List<Proveedor> provs = proveRepo.findAll();

        return provs.stream().map(this::mapearADTO).toList();
    }

    public void eliminarProveedor(Integer id) {
        proveRepo.deleteById(id);
    }

    public void actualizarProveedor(Integer id, Proveedor pro) {
        Proveedor prove = proveRepo.findById(id).orElse(null);

        if (prove != null) {
            prove.setRut(pro.getRut());
            prove.setNombre(pro.getNombre());
            prove.setTelefono(pro.getTelefono());
            prove.setNombreContacto(pro.getNombreContacto());
            prove.setOrdenes(pro.getOrdenes());
            proveRepo.save(prove);
        }
    }
}
