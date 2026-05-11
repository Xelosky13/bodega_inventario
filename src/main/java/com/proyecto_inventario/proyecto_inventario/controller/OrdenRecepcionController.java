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
import com.proyecto_inventario.proyecto_inventario.DTO.OrdenRecepcionDTO;
import com.proyecto_inventario.proyecto_inventario.service.OrdenRecepcionService;

@RestController
@RequestMapping("/api/v1/ordenrecepcion")
public class OrdenRecepcionController {
    @Autowired
    private OrdenRecepcionService service;

    @PostMapping
    public ResponseEntity<OrdenRecepcionDTO> guardar(@RequestBody OrdenRecepcionDTO dto) {
        OrdenRecepcionDTO response = service.guardarOrden(dto);
        if (response == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenRecepcionDTO> buscarPorId(@PathVariable Integer id) {
        OrdenRecepcionDTO dto = service.buscarOrdenPorId(id);
        if (dto == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/proveedor/{id}")
    public ResponseEntity<OrdenRecepcionDTO> buscarPorproveedor(@PathVariable Integer id) {
        OrdenRecepcionDTO dto = service.buscarPorProveedor(id);
        if (dto == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(dto);

    }

    @DeleteMapping
    public void eliminarOrden(@PathVariable Integer id) {
        service.EliminarOrden(id);
    }

    @PutMapping("/{id}")
    public void actualizarOrden(@PathVariable Integer id, @RequestBody OrdenRecepcionDTO dto) {
        service.actualizarOrden(id, dto);
    }

    @GetMapping
    public List<OrdenRecepcionDTO> listarOrdenes() {
        return service.listarOrdenes();
    }

}
