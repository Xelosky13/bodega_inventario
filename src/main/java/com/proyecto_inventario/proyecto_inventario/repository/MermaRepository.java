package com.proyecto_inventario.proyecto_inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proyecto_inventario.proyecto_inventario.model.Merma;

@Repository
public interface MermaRepository extends JpaRepository<Merma, Integer> {

}