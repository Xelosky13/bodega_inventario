package com.proyecto_inventario.proyecto_inventario.DTO;

import java.time.LocalDate;

import lombok.Data;

@Data
public class MermaDTO {

    private Integer id;
    private Integer productoId;
    private String nombreProducto;
    private Integer cantidad;
    private String motivo;
    private LocalDate fechaReporte;
}