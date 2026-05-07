package com.proyecto_inventario.proyecto_inventario.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto_inventario.proyecto_inventario.model.Ubicacion;

@Repository
public interface UbicacionRepository extends JpaRepository<Ubicacion, Integer> {
    public Ubicacion findUbicacionById(Integer id);

    public ArrayList<Ubicacion> findAll();

    public ArrayList<Ubicacion> findByPasillo(Integer pasillo);

    public ArrayList<Ubicacion> findByDescripcionAllIgnoreCase(String descripcion);

    public ArrayList<Ubicacion> findByEstante(Integer estante);

    public void deleteById(Integer id);

}
