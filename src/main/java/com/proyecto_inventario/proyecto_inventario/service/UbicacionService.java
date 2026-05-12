package com.proyecto_inventario.proyecto_inventario.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto_inventario.proyecto_inventario.DTO.UbicacionDTO;
import com.proyecto_inventario.proyecto_inventario.model.Ubicacion;
import com.proyecto_inventario.proyecto_inventario.repository.UbicacionRepository;

@Service
public class UbicacionService {
    @Autowired
    private UbicacionRepository repository;

    public UbicacionDTO mappearADto(Ubicacion ubi) {
        UbicacionDTO dto = new UbicacionDTO();
        dto.setDescripcion(ubi.getDescripcion());
        dto.setEstante(ubi.getEstante());
        dto.setPasillo(ubi.getPasillo());
        return dto;

    }

    public UbicacionDTO buscarPorId(Integer id) {
        Ubicacion ubicacion = repository.findById(id).orElse(null);

        return this.mappearADto(ubicacion);
    }

    public List<UbicacionDTO> ubicaciones() {
        List<Ubicacion> ubis = repository.findAll();
        return ubis.stream().map(this::mappearADto).toList();
    }

    public List<UbicacionDTO> buscarPorPasillo(Integer pasillo) {
        List<Ubicacion> ubis = repository.findByPasillo(pasillo);
        return ubis.stream().map(this::mappearADto).toList();

    }

    public List<UbicacionDTO> buscarPorEstante(Integer estante) {
        List<Ubicacion> ubis = repository.findByEstante(estante);
        return ubis.stream().map(this::mappearADto).toList();
    }

    public void eliminarPorId(Integer id) {
        repository.deleteById(id);

    }

    public UbicacionDTO guardarUbicacion(UbicacionDTO ubi) {
        Ubicacion ubica = new Ubicacion();

        ubica.setDescripcion(ubi.getDescripcion());
        ubica.setEstante(ubi.getEstante());
        ubica.setPasillo(ubi.getPasillo());

        Ubicacion guardado = repository.save(ubica);
        return this.mappearADto(guardado);
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
