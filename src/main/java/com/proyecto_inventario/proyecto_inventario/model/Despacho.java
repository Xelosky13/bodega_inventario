package com.proyecto_inventario.proyecto_inventario.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "despachos")
public class Despacho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "La fecha de salida es obligatoria")
    @PastOrPresent(message = "La fecha no puede ser futura")
    @Column(nullable = false)
    private LocalDateTime fechaSalida;

    @NotBlank(message = "El transportista o empresa es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres" )
    @Column(nullable = false)
    private String transportista;

    @NotBlank(message = "La patente del vehiculo es obligatoria")
    @Size(min = 6, max = 10, message = "La patente debe tener entre 6 y 10 caracteres")
    @Column(nullable = false, length = 100)
    private String patenteVehiculo;

    @Size(max = 250)
    private String observaciones;

    @OneToOne
    @JoinColumn(name = "picking_id", nullable = false, unique = true)
    @NotNull(message = "Un despacho debe estar asociado a un proceso de picking")
    private Picking picking;

}
