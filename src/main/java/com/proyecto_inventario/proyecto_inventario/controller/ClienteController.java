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

import com.proyecto_inventario.proyecto_inventario.DTO.ClienteDTO;
import com.proyecto_inventario.proyecto_inventario.Service.ClienteService;
import com.proyecto_inventario.proyecto_inventario.model.Cliente;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> todosLosClientes(){
        List<ClienteDTO> clientes = clienteService.obtenerTodos();
        if(clientes.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscarPorId(@PathVariable Integer id){
        try {
            ClienteDTO cliente = clienteService.buscarPorId(id);
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("rut/{rut}")
    public ResponseEntity<ClienteDTO> buscarPorRut(@PathVariable String rut){
        try {
            ClienteDTO cliente = clienteService.buscarPorRut(rut);
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping
    public ResponseEntity<Cliente> agregarCliente(@RequestBody Cliente cliente) {
        try {
            Cliente guardado = clienteService.guardarCliente(cliente);
            return new ResponseEntity<>(guardado, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }





}
