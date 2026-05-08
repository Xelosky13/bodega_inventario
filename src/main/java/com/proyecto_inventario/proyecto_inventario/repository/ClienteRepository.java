package com.proyecto_inventario.proyecto_inventario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyecto_inventario.proyecto_inventario.model.Cliente;
import com.proyecto_inventario.proyecto_inventario.model.Despacho;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
    List<Cliente> findByRut(String rut);

    @Query("SELECT d FROM Despacho d WHERE d.picking.pedido.cliente.nombre = :nombreCliente")
    List<Despacho> buscarDespachosPorNombreCliente(@Param("nombreCliente") String nombreCliente);
}
