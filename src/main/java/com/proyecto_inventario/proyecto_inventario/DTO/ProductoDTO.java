package com.proyecto_inventario.proyecto_inventario.DTO;

import lombok.Data;

@Data
public class ProductoDTO {
    private Integer id;
    private String nombre;
    private String sku;
    private UbicacionDTO ubicacion;
}
