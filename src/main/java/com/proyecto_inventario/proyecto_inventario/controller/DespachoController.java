package com.proyecto_inventario.proyecto_inventario.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto_inventario.proyecto_inventario.DTO.DespachoDTO;
import com.proyecto_inventario.proyecto_inventario.model.Despacho;
import com.proyecto_inventario.proyecto_inventario.service.DespachoService;

import jakarta.validation.Valid;

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

    @GetMapping("buscar-pantente-y-fecha/")
    public ResponseEntity<List<DespachoDTO>> buscarDespachos(@RequestParam String patente, LocalDate fecha){
        List<DespachoDTO> resultados = despachoService.buscarPorCamionYFecha(patente, fecha);
        if(resultados.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(resultados);
    }

    @PostMapping
    public ResponseEntity<Despacho> agregarDespacho(@Valid @RequestBody Despacho despacho) {
        try {
            Despacho guardado = despachoService.guardarDespacho(despacho);
            return new ResponseEntity<>(guardado, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Despacho> editarDespacho(@PathVariable Integer id, @Valid @RequestBody Despacho despacho) {
        try {
            Despacho editado = despachoService.guardarDespacho(despacho);
            return new ResponseEntity<>(editado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }    

    @PutMapping("/{id}")
    public ResponseEntity<Despacho> actualizarCliente(@PathVariable Integer id,@Valid @RequestBody Despacho despacho){
        try{
            Despacho actualizado = despachoService.actualizarDespacho( id, despacho);
            return new ResponseEntity<>(actualizado, HttpStatus.OK);
        }catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarDespacho(@PathVariable Integer id) {
        String resultado = despachoService.eliminar(id);
        
        if (resultado.contains("exitosamente")) {
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscar-por-cliente")
    public ResponseEntity<List<DespachoDTO>> buscarPorCliente(@RequestParam String nombre){
        List<DespachoDTO> resultados = despachoService.buscarPorNombreCliente(nombre);
        if(resultados.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(resultados);
    }
   
}
