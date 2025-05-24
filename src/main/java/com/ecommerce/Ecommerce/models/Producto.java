package com.ecommerce.Ecommerce.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "productos")
@Data
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String referencia;
    private String descripcion;
    private int cantidad;
    private int precio;
    @ManyToOne
    @JoinColumn(name = "categorias_id", nullable = false, foreignKey = @ForeignKey(name = "fk_producto_categoria"))
    private Categoria categoria;
}
