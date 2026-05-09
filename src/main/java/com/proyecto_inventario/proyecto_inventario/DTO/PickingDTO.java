package com.proyecto_inventario.proyecto_inventario.DTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PickingDTO {

    private Integer id;
    private Integer operarioId;
    private String nombreOperario;
    private Integer pedidoId;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private String estado;
}