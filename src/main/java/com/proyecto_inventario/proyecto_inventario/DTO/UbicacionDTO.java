package com.proyecto_inventario.proyecto_inventario.DTO;

import lombok.Data;

@Data
public class UbicacionDTO {
    private Integer id;
    private Integer pasillo;
    private Integer estante;
    private String descripcion;
}
