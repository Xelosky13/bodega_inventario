package com.proyecto_inventario.proyecto_inventario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto_inventario.proyecto_inventario.DTO.ItemPedidoDTO;
import com.proyecto_inventario.proyecto_inventario.Service.ItemPedidoService;
import com.proyecto_inventario.proyecto_inventario.model.ItemPedido;

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
    public ResponseEntity<ItemPedido> agregarItemPedido(@RequestBody ItemPedido item) {
        try {
            ItemPedido guardado = itemPedidoService.guardarItemPedido(item);
            return new ResponseEntity<>(guardado, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    
}
