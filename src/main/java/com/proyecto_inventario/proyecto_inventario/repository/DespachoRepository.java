package com.proyecto_inventario.proyecto_inventario.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proyecto_inventario.proyecto_inventario.model.Despacho;

public interface DespachoRepository extends JpaRepository<Despacho, Integer>{
    List<Despacho> findByPatenteVehiculo(String patenteVehiculo);

    @Query("SELECT d FROM Despacho d WHERE d.pantenteVehiculo = :patente AND d.fechaSalida")
    List<Despacho> buscarPorCamionYFecha(@Param("patente") String patente, @Param("fecha") LocalDateTime fecha);

}
