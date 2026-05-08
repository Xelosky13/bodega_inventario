package com.proyecto_inventario.proyecto_inventario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto_inventario.proyecto_inventario.DTO.DespachoDTO;
import com.proyecto_inventario.proyecto_inventario.Service.DespachoService;

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
}
