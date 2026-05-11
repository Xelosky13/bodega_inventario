package com.proyecto_inventario.proyecto_inventario.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "pickings")
public class Picking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private LocalDateTime fechaInicio;

    @PrePersist
    protected void onCreate() {
        this.fechaInicio = LocalDateTime.now();
    }

    @NotBlank(message = "El estado del picking es obligatorio")
    @Size(min = 10, max = 11, message = "Estado debe ser En Proceso o Completado")
    @Column(nullable = false, length = 11)
    private String estado;

    @OneToOne
    @JoinColumn(name = "pedido_id", nullable = false, unique = true)
    @NotNull(message = "Se debe asignar un pedido")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "operario_id", nullable = false)
    @NotNull(message = "Se debe asignar un operario al picking")
    private Operario operario;
}
