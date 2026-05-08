package com.proyecto_inventario.proyecto_inventario.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto_inventario.proyecto_inventario.model.OrdenRecepcion;

@Repository
public interface OrdenRecepcionRepository extends JpaRepository<OrdenRecepcion, Integer> {

    public OrdenRecepcion findByfechaRecepcion(LocalDate fechaRecepcion);

    public OrdenRecepcion findByProveedor(Integer Id);
}
