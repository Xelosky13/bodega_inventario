package com.proyecto_inventario.proyecto_inventario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proyecto_inventario.proyecto_inventario.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    @Query(value = "SELECT * FROM productos WHERE stock_actual <= :limite ORDER BY stock_actual ASC", nativeQuery = true)
    List<Producto> buscarStockCritico(@Param("limite") Integer limite);

    @Modifying
    @Query("UPDATE Producto p SET p.stockActual = p.stockActual - :cantidad WHERE p.id = :id AND p.stockActual >= :cantidad")
    int descontarStock(@Param("id") Integer productoId, @Param("cantidad") Integer cantidad);
}
