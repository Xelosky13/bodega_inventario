package com.proyecto_inventario.proyecto_inventario.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pedidos_asignados")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "La fecha es obligatoria")
    @PastOrPresent(message = "La fecha no puede ser futura")
    @Column(nullable = false)
    private LocalDateTime fechaPedido;

    @NotBlank(message = "El estado del pedido es obligatorio")
    @Size(min = 7, max = 13, message = "Estado debe ser Pendiente, Picking o Completado" )
    @Column(nullable = false, length = 13)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    @NotNull(message = "El pedido debe estar asociado a un cliente")
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> items;


    @OneToOne(mappedBy = "pedido")
    private Picking picking;
}
