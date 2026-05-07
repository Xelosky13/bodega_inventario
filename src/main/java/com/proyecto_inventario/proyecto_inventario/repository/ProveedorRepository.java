package com.proyecto_inventario.proyecto_inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proyecto_inventario.proyecto_inventario.model.Proveedor;
import java.util.ArrayList;

@Repository

public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {

    public Proveedor FindById(Integer id);

    public Proveedor findByNombre(String nombre);

    public Proveedor findByRut(String rut);

    public Proveedor findByTelefono(String telefono);

    public ArrayList<Proveedor> findAll();

    public void deleteById(Integer id);

    public Proveedor findByOrdenRecepcionId(Integer id);

}
