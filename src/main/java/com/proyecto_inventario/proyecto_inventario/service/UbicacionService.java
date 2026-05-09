package com.proyecto_inventario.proyecto_inventario.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto_inventario.proyecto_inventario.model.Ubicacion;
import com.proyecto_inventario.proyecto_inventario.repository.UbicacionRepository;

@Service
public class UbicacionService {
    @Autowired
    private UbicacionRepository repository;

    public Ubicacion buscarPorId(Integer id) {
        Ubicacion ubicacion = repository.findUbicacionById(id);

        return ubicacion;
    }

    public List<Ubicacion> ubicaciones() {
        return repository.findAll();
    }

    public ArrayList<Ubicacion> buscarPorPasillo(Integer pasillo) {
        return repository.findByPasillo(pasillo);

    }

    public ArrayList<Ubicacion> buscarPorDescripcion(String descripcion) {
        return repository.findByDescripcionAllIgnoreCase(descripcion);
    }

    public ArrayList<Ubicacion> buscarPorEstante(Integer estante) {
        return repository.findByEstante(estante);
    }

    public void eliminarPorId(Integer id) {
        repository.deleteById(id);

    }

    public Ubicacion guardarUbicacion(Ubicacion ubi) {
        return repository.save(ubi);
    }

    public void actualizarUbicacion(Integer id, Ubicacion ubi) {
        Ubicacion ubica = repository.findById(id).orElse(null);

        if (ubica != null) {
            ubica.setDescripcion(ubi.getDescripcion());
            ubica.setEstante(ubi.getEstante());
            ubica.setPasillo(ubi.getPasillo());
            ubica.setDescripcion(ubi.getDescripcion());

            repository.save(ubica);
        }
    }

}
