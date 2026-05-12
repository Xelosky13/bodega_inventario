package com.proyecto_inventario.proyecto_inventario.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proyecto_inventario.proyecto_inventario.model.Despacho;

public interface DespachoRepository extends JpaRepository<Despacho, Integer>{
    List<Despacho> findByPatenteVehiculo(String patenteVehiculo);

    @Query("SELECT d FROM Despacho d WHERE d.patenteVehiculo = :patente AND d.fechaSalida = :fecha")
    List<Despacho> buscarPorCamionYFecha(@Param("patente") String patente, @Param("fecha") LocalDate fecha);
    
    @Query("SELECT d FROM Despacho d WHERE d.picking.pedido.cliente.nombre = :nombreCliente")
    List<Despacho> buscarDespachosPorNombreCliente(@Param("nombreCliente") String nombreCliente);
}
