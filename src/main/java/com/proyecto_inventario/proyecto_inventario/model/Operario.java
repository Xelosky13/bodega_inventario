package com.proyecto_inventario.proyecto_inventario.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "operarios")
public class Operario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El RUT del operario es obligatorio")
    @Size(min = 8, max = 10, message = "RUT debe tener entre 8 y 10 caracteres")
    @Column(unique = true, nullable = false, length = 10)
    private String rut;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 100, message = "Nombre debe tener entre 3 y 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nombre;

    @NotBlank(message = "EL turno es obligatorio")
    @Size(min = 5, max = 6, message = "Turno debe ser Mañana , Tarde O Noche?")
    @Column(nullable = false, length = 6)

    @OneToMany(mappedBy = "operario")
    private List<Picking> pedidosAsignados;
}
