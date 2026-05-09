package com.proyecto_inventario.proyecto_inventario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.proyecto_inventario.proyecto_inventario.dto.MermaDTO;
import com.proyecto_inventario.proyecto_inventario.model.Merma;
import com.proyecto_inventario.proyecto_inventario.service.MermaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/mermas")
public class MermaController {

    @Autowired
    private MermaService mermaService;

    @PostMapping
    public ResponseEntity<?> registrarMerma(@Valid @RequestBody MermaDTO mermaDTO) {
        try {
            Merma mermaGuardada = mermaService.registrarMerma(mermaDTO);
            return new ResponseEntity<>(mermaGuardada, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}