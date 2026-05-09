package com.proyecto_inventario.proyecto_inventario.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.proyecto_inventario.proyecto_inventario.DTO.ItemPedidoDTO;
import com.proyecto_inventario.proyecto_inventario.Service.ItemPedidoService;
import com.proyecto_inventario.proyecto_inventario.model.ItemPedido;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/items")
public class ItemPedidoController {

    @Autowired
    private ItemPedidoService itemPedidoService;

    @GetMapping
    public ResponseEntity<List<ItemPedidoDTO>> todosLosClientes(){
        List<ItemPedidoDTO> items = itemPedidoService.obtenerTodos();
        if(items.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemPedidoDTO> buscarPorId(@PathVariable Integer id){
        try {
            ItemPedidoDTO item = itemPedidoService.buscarPorId(id);
            return new ResponseEntity<>(item, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ItemPedido> agregarItemPedido(@Valid @RequestBody ItemPedido item) {
        try {
            ItemPedido guardado = itemPedidoService.guardarItemPedido(item);
            return new ResponseEntity<>(guardado, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ItemPedido> editarItemPedido(@PathVariable Integer id, @Valid @RequestBody ItemPedido itemPedido) {
        try {
            ItemPedido editado = itemPedidoService.guardarItemPedido(itemPedido);
            return new ResponseEntity<>(editado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }    

    @PutMapping("/{id}")
    public ResponseEntity<ItemPedido> actualizarItemPedido(@PathVariable Integer id, @Valid @RequestBody ItemPedido itemPedido){
        try{
            ItemPedido actualizado = itemPedidoService.actualizarItemPedido( id, itemPedido);
            return new ResponseEntity<>(actualizado, HttpStatus.OK);
        }catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarItemPedido(@PathVariable Integer id) {
        String resultado = itemPedidoService.eliminar(id);
        
        if (resultado.contains("exitosamente")) {
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/total-unidades/{idPedido}")
    public ResponseEntity<Integer> totalUnidades(@PathVariable Integer idPedido){
        Integer total = itemPedidoService.obtenerTotalUnidadesPorPedido(idPedido);
        return ResponseEntity.ok(total);
    }
}
