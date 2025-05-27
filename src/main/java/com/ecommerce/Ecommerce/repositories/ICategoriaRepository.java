package com.ecommerce.Ecommerce.repositories;

import com.ecommerce.Ecommerce.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoriaRepository extends JpaRepository<Categoria,Long> {
}
