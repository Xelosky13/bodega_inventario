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
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El RUT es obligatorio")
    @Size(min = 8, max = 12, message = "El RUT debe tener entre 8 y 12 caracteres")
    @Column(unique = true, nullable = false, length = 12)
    private String rut;

    @NotBlank(message = "El nombre del cliente es obligatorio")
    @Size(min = 3, max = 100, message = "Nombre debe tener entre 8 y 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nombre;

    @NotBlank(message = "La direccion de despacho es obligatoria")
    @Size(min = 7, max = 200, message = "La dirreccion de tener entre 7 y 200 caracteres")
    @Column(nullable = false, length = 200)
    private String direccion;

    @OneToMany(mappedBy = "cliente")
    private List<Pedido> historialPedidos;
}
