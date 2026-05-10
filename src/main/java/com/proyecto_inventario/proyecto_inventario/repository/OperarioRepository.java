package com.proyecto_inventario.proyecto_inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.proyecto_inventario.proyecto_inventario.model.Operario;

public interface OperarioRepository extends JpaRepository<Operario, Integer> {
}