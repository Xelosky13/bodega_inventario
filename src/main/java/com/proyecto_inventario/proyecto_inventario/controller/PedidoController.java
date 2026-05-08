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

import com.proyecto_inventario.proyecto_inventario.DTO.PedidoDTO;
import com.proyecto_inventario.proyecto_inventario.Service.PedidoService;
import com.proyecto_inventario.proyecto_inventario.model.Pedido;

@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> todosLosClientes(){
        List<PedidoDTO> pedidos = pedidoService.obtenerTodos();
        if(pedidos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }  

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> buscarPorId(@PathVariable Integer id){
        try {
            PedidoDTO pedido = pedidoService.buscarPorId(id);
            return new ResponseEntity<>(pedido, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Pedido> agregarPedido(@RequestBody Pedido pedido) {
        try {
            Pedido guardado = pedidoService.guardarPedido(pedido);
            return new ResponseEntity<>(guardado, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
