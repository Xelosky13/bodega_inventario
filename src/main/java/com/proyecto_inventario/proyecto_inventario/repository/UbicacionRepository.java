package com.proyecto_inventario.proyecto_inventario.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto_inventario.proyecto_inventario.model.Ubicacion;

@Repository
public interface UbicacionRepository extends JpaRepository<Ubicacion, Integer> {
    public Optional<Ubicacion> findById(Integer id);

    public ArrayList<Ubicacion> ubicaciones();

    public Ubicacion buscarPorPasillo(Integer pasillo);

    public ArrayList<Ubicacion> buscarPorDescripcion(String descripcion);

    public ArrayList<Ubicacion> buscarporEstante(Integer estante);

    public void eliminarUbicación(Integer id);

    public Ubicacion actualizarUbicacion(Integer id);

}
