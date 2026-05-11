package com.proyecto_inventario.proyecto_inventario.DTO;

import java.time.LocalDate;
import java.util.List;

import com.proyecto_inventario.proyecto_inventario.model.DetalleRecepcion;
import com.proyecto_inventario.proyecto_inventario.model.Proveedor;

import lombok.Data;

@Data
public class OrdenRecepcionDTO {
    private LocalDate fechaRecepcion;
    private Proveedor provedor;
    private List<DetalleRecepcion> detalles;

}
