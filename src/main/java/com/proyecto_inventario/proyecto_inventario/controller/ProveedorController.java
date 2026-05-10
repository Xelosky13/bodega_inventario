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

import com.proyecto_inventario.proyecto_inventario.DTO.ProveedorDTO;
import com.proyecto_inventario.proyecto_inventario.model.Proveedor;
import com.proyecto_inventario.proyecto_inventario.service.ProveedorService;

@RestController
@RequestMapping("/api/v1/proveedor")
public class ProveedorController {
    @Autowired
    private ProveedorService service;

    @PostMapping()
    public ResponseEntity<ProveedorDTO> guardar(@RequestBody ProveedorDTO dto) {
        ProveedorDTO response = service.guardarProveedor(dto);
        if (response == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProveedorDTO> buscarPorId(@PathVariable Integer id) {
        ProveedorDTO dto = service.buscarPorId(id);
        if (dto == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(dto);

    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<ProveedorDTO>> buscarPornombre(@PathVariable String nombre) {
        List<ProveedorDTO> dto = service.buscarPorNombre(nombre);
        if (dto == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/orden/{orden}")
    public ProveedorDTO buscarPorOrdenes(@PathVariable Integer id) {
        return service.buscarPorOrdenes(id);

    }

    @GetMapping()
    public List<ProveedorDTO> Proveedores() {
        return service.Proveedores();
    }

    @DeleteMapping("/{id}")
    public void eliminarProveedor(Integer id) {
        service.eliminarProveedor(id);
    }

    @PutMapping("/{id}")
    public void actualizarProveedor(@PathVariable Integer id, @RequestBody Proveedor pro) {
        service.actualizarProveedor(id, pro);
    }

}