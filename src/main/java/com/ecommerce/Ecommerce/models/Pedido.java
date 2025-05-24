package com.ecommerce.Ecommerce.models;


import jakarta.persistence.*;

@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String direccion;
    private int total;
    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false, foreignKey = @ForeignKey(name = "fk_pedidos_users"))
    private User user;

}