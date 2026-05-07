package com.proyecto_inventario.proyecto_inventario.DTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class DesoachoDTO {
    private Integer id;
    private LocalDateTime fechaSalida;
    private String transportista;
    private String patenteVehiculo;
    private Integer idPedido;
    private String nombreCliente;
}
