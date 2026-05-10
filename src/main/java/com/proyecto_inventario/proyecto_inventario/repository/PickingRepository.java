package com.proyecto_inventario.proyecto_inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.proyecto_inventario.proyecto_inventario.model.Picking;

public interface PickingRepository extends JpaRepository<Picking, Integer> {
}