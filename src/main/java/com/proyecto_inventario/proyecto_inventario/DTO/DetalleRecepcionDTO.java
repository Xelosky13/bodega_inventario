package com.proyecto_inventario.proyecto_inventario.DTO;

import com.proyecto_inventario.proyecto_inventario.model.OrdenRecepcion;
import com.proyecto_inventario.proyecto_inventario.model.Producto;

import lombok.Data;

@Data
public class DetalleRecepcionDTO {
    private String estado;
    private Integer cantidad;
    private Producto producto;
    private OrdenRecepcion orden;

}
