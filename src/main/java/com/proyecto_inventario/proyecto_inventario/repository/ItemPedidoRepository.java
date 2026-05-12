package com.proyecto_inventario.proyecto_inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyecto_inventario.proyecto_inventario.model.ItemPedido;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer>{
    @Query("SELECT  SUM(i.cantidad) FROM ItemPedido i where i.pedido.id = :idPedido")
    Integer sumarTotalProductos(@Param("idPedido") Integer idPedido);
}
