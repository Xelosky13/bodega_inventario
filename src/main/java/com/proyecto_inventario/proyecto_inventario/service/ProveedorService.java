package com.proyecto_inventario.proyecto_inventario.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto_inventario.proyecto_inventario.model.Proveedor;
import com.proyecto_inventario.proyecto_inventario.repository.ProveedorRepository;

@Service
public class ProveedorService {
    @Autowired
    private ProveedorRepository proveRepo;

    public Proveedor buscarPorId(Integer id) {
        return proveRepo.FindById(id);

    }

    public Proveedor buscarPorNombre(String nombre) {
        return proveRepo.findByNombre(nombre);
    }

    public Proveedor buscarPorRut(String rut) {
        return proveRepo.findByRut(rut);
    }

    public Proveedor buscarPorOrdenes(Integer id) {
        return proveRepo.findByOrdenRecepcionId(id);

    }

    public Proveedor buscarPorTelefono(String telefono) {
        return proveRepo.findByTelefono(telefono);

    }

    public List<Proveedor> Proveedores() {
        return proveRepo.findAll();
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
