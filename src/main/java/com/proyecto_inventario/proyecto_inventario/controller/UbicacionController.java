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
import com.proyecto_inventario.proyecto_inventario.DTO.UbicacionDTO;
import com.proyecto_inventario.proyecto_inventario.model.Ubicacion;
import com.proyecto_inventario.proyecto_inventario.service.UbicacionService;

@RestController
@RequestMapping("/api/v1/ubicacion")
public class UbicacionController {
    @Autowired
    private UbicacionService service;

    @PostMapping
    public ResponseEntity<UbicacionDTO> guardar(@RequestBody UbicacionDTO dto) {
        UbicacionDTO response = service.guardarUbicacion(dto);
        if (response == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UbicacionDTO> buscarPorId(@PathVariable Integer id) {
        UbicacionDTO dto = service.buscarPorId(id);
        if (dto == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public List<UbicacionDTO> ubicaciones() {

        return service.ubicaciones();

    }

    @DeleteMapping
    public void eliminarPorId(Integer id) {
        service.eliminarPorId(id);
    }

    @PutMapping("/{id}")
    public void actualizarUbicacion(@PathVariable Integer id, @RequestBody Ubicacion ubi) {
        service.actualizarUbicacion(id, ubi);

    }

}
