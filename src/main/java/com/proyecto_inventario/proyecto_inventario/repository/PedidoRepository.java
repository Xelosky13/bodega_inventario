package com.proyecto_inventario.proyecto_inventario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyecto_inventario.proyecto_inventario.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
    @Query("SELECT p FROM Pedido p WHERE p.cliente.rut = :rut AND p.estado = 'PENDIENTE'")
    List<Pedido> buscarPendientesPorCliente(@Param("rut") String rut);
}
