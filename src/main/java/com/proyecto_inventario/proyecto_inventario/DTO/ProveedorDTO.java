package com.proyecto_inventario.proyecto_inventario.DTO;

import java.util.List;

import com.proyecto_inventario.proyecto_inventario.model.OrdenRecepcion;

import lombok.Data;

@Data
public class ProveedorDTO {
    private String nombre;
    private String rut;
    private String telefono;
    private String nombreContacto;
    private List<OrdenRecepcion> ordenes;
}
