package com.proyecto_inventario.proyecto_inventario.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto_inventario.proyecto_inventario.DTO.DespachoDTO;
import com.proyecto_inventario.proyecto_inventario.Service.DespachoService;
import com.proyecto_inventario.proyecto_inventario.model.Despacho;

@RestController
@RequestMapping("/api/v1/despachos")
public class DespachoController {
    
    @Autowired
    private DespachoService despachoService;

    @GetMapping
    public ResponseEntity<List<DespachoDTO>> todosLosClientes(){
        List<DespachoDTO> despachos = despachoService.obtenerTodos();
        if(despachos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(despachos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DespachoDTO> buscarPorId(@PathVariable Integer id){
        try {
            DespachoDTO despacho = despachoService.buscarPorId(id);
            return new ResponseEntity<>(despacho, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("patente/{patente}")
    public ResponseEntity<List<DespachoDTO>> buscarPorPatente(@PathVariable String patente){
        List<DespachoDTO> despachos = despachoService.buscarPorPatente(patente);
        if(despachos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(despachos);
    }

    @GetMapping("buscarPantente&Fecha/")
    public ResponseEntity<List<DespachoDTO>> buscarDespachos(@RequestParam String patente, LocalDate fecha){
        List<DespachoDTO> resultados = despachoService.buscarPorCamionYFecha(patente, fecha);
        if(resultados.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(resultados);
    }

    @PostMapping
    public ResponseEntity<Despacho> agregarDespacho(@RequestBody Despacho despacho) {
        try {
            Despacho guardado = despachoService.guardarDespacho(despacho);
            return new ResponseEntity<>(guardado, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    
}
