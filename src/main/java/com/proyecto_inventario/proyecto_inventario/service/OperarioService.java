package com.proyecto_inventario.proyecto_inventario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto_inventario.proyecto_inventario.model.Operario;
import com.proyecto_inventario.proyecto_inventario.repository.OperarioRepository;

@Service
public class OperarioService {

    @Autowired
    private OperarioRepository operarioRepository;

    public List<Operario> listarTodos() {
        return operarioRepository.findAll();
    }

    public Operario obtenerPorId(Integer id) {
        return operarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Operario no encontrado con id: " + id));
    }

    public Operario crear(Operario operario) {
        return operarioRepository.save(operario);
    }

    public Operario actualizar(Integer id, Operario operario) {
        Operario existente = operarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Operario no encontrado con id: " + id));
        existente.setNombre(operario.getNombre());
        existente.setRut(operario.getRut());
        existente.setTurno(operario.getTurno());
        return operarioRepository.save(existente);
    }

    public void eliminar(Integer id) {
        if (!operarioRepository.existsById(id)) {
            throw new RuntimeException("Operario no encontrado con id: " + id);
        }
        operarioRepository.deleteById(id);
    }
}