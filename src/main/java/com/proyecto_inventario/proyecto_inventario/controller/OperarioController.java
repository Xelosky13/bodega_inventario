package com.proyecto_inventario.proyecto_inventario.controller;

import com.proyecto_inventario.proyecto_inventario.model.Operario;
import com.proyecto_inventario.proyecto_inventario.service.OperarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/operarios")
public class OperarioController {

    @Autowired
    private OperarioService operarioService;

    @GetMapping
    public ResponseEntity<List<Operario>> listarTodos() {
        return ResponseEntity.ok(operarioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Integer id) {
        try {
            Operario operario = operarioService.obtenerPorId(id);
            return ResponseEntity.ok(operario);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Operario operario) {
        try {
            Operario creado = operarioService.crear(operario);
            return ResponseEntity.status(HttpStatus.CREATED).body(creado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id,
                                        @RequestBody Operario operario) {
        try {
            Operario actualizado = operarioService.actualizar(id, operario);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            operarioService.eliminar(id);
            return ResponseEntity.ok("Operario eliminado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}