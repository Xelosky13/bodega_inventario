package com.proyecto_inventario.proyecto_inventario.DTO;

import java.util.List;

import com.proyecto_inventario.proyecto_inventario.model.Producto;

import lombok.Data;

@Data
public class UbicacionDTO {
    private Integer id;
    private Integer pasillo;
    private Integer estante;
    private String descripcion;
    private List<Producto> productos;
}
