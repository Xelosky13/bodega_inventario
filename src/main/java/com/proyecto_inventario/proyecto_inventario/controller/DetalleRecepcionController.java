package com.proyecto_inventario.proyecto_inventario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.proyecto_inventario.proyecto_inventario.DTO.DetalleRecepcionDTO;
import com.proyecto_inventario.proyecto_inventario.service.DetalleRecepcionService;

@RestController
@RequestMapping("/api/v1/detallerecepcion")
public class DetalleRecepcionController {
    @Autowired
    private DetalleRecepcionService service;

    @PostMapping
    public ResponseEntity<DetalleRecepcionDTO> guardar(@RequestBody DetalleRecepcionDTO dto) {
        DetalleRecepcionDTO response = service.guardarDetalleRecepcion(dto);

        if (response == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleRecepcionDTO> buscarPorId(@PathVariable Integer id) {
        DetalleRecepcionDTO dto = service.buscarPorId(id);
        if (dto == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(dto);

    }

    @GetMapping("/{estado}")
    public ResponseEntity<List<DetalleRecepcionDTO>> buscarPorestado(@PathVariable String estado) {
        List<DetalleRecepcionDTO> dto = service.buscarPorEstado(estado);
        if (dto == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(dto);

    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleRecepcionDTO> buscarPorOrden(@PathVariable Integer id) {
        DetalleRecepcionDTO dto = service.buscarPorOrden(id);
        if (dto == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(dto);

    }

    @DeleteMapping("/{id}")
    public void eliminarOrden(@PathVariable Integer id) {
        service.deleteById(id);
    }

    @PutMapping("/{id}")
    public void actualizarDetalle(@PathVariable Integer id, @RequestBody DetalleRecepcionDTO detalle) {
        service.actualizarDetalleRecepcion(id, detalle);
    }

}
