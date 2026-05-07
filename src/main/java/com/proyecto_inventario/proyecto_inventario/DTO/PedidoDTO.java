package com.proyecto_inventario.proyecto_inventario.DTO;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class PedidoDTO {
    private Integer id;
    private LocalDateTime fechaPedido;
    private String nombreCliente;
    private String estado;
    private List<ItemPedidoDTO> items;
}
