package com.proyecto_inventario.proyecto_inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.proyecto_inventario.proyecto_inventario.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}