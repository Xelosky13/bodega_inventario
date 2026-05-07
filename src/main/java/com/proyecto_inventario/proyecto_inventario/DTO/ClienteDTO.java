package com.proyecto_inventario.proyecto_inventario.DTO;

import java.util.List;

import com.proyecto_inventario.proyecto_inventario.model.Pedido;

import lombok.Data;

@Data
public class ClienteDTO {
    private Integer id;
    private String rut;
    private String nombre;
    private List<Pedido> historialPedidos;
    
}
