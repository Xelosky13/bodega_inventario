package com.proyecto_inventario.proyecto_inventario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proyecto_inventario.proyecto_inventario.model.Proveedor;

@Repository

public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {

    public List<Proveedor> findByNombre(String nombre);

    public Proveedor findByRut(String rut);

    public Proveedor findByTelefono(String telefono);

    public Proveedor findByOrdenRecepcionId(Integer id);

}
