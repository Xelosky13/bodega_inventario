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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto_inventario.proyecto_inventario.DTO.PedidoDTO;
import com.proyecto_inventario.proyecto_inventario.model.Pedido;
import com.proyecto_inventario.proyecto_inventario.service.PedidoService;

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
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Pedido> editarPedido(@PathVariable Integer id, @RequestBody Pedido pedido) {
        try {
            Pedido editado = pedidoService.guardarPedido(pedido);
            return new ResponseEntity<>(editado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Pedido> actualizarPedido(@PathVariable Integer id, @RequestBody Pedido pedido){
        try{
            Pedido actualizado = pedidoService.actualizarPedido( id, pedido);
            return new ResponseEntity<>(actualizado, HttpStatus.OK);
        }catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPedido(@PathVariable Integer id) {
        String resultado = pedidoService.eliminar(id);
        
        if (resultado.contains("exitosamente")) {
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/pendientes/{rut}")
    public ResponseEntity<List<PedidoDTO>> listarPendientes(@PathVariable String rut){
        List<PedidoDTO> lista = pedidoService.obtenerPendientesPorCliente(rut);
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/agregar-item")
    public ResponseEntity<String> agregarItem(
            @RequestParam Integer pedidoId,
            @RequestParam Integer productoId,
            @RequestParam Integer cantidad){
        try {
            String mensaje = pedidoService.añadirItemAPedido(pedidoId, productoId, cantidad);
            return ResponseEntity.ok(mensaje);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }    
    }
}
