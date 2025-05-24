package com.ecommerce.Ecommerce.models;

import jakarta.persistence.*;

@Entity
@Table(name = "lineas_pedidos")
public class LineaPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "productos_id", nullable = false, foreignKey = @ForeignKey(name = "fk_lineas_pedidos_productos"))
    private Producto producto;
    @ManyToOne
    @JoinColumn(name = "pedidos_id", nullable = false, foreignKey = @ForeignKey(name = "fk_lineas_pedidos_pedidos"))
    private Pedido pedido;
    private int cantidad;
    private int precio;
}
