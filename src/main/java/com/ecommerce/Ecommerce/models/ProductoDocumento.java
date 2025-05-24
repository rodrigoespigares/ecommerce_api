package com.ecommerce.Ecommerce.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "productos_documentos")
@Data
public class ProductoDocumento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String file_name;
    private String user_name;
    @ManyToOne
    @JoinColumn(name = "productos_id", nullable = false, foreignKey = @ForeignKey(name = "fk_producto_documento_producto"))
    private Producto producto;
}
