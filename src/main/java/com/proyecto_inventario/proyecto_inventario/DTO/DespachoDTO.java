package com.proyecto_inventario.proyecto_inventario.DTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class DespachoDTO {
    private Integer id;
    private LocalDateTime fechaSalida;
    private String transportista;
    private String patenteVehiculo;
    private Integer idPedido;
    private String nombreCliente;
}
