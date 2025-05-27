package com.ecommerce.Ecommerce.services;

import com.ecommerce.Ecommerce.models.Categoria;
import com.ecommerce.Ecommerce.repositories.ICategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private ICategoriaRepository categoriaRepository;

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Categoria findById(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con id: " + id));
    }
    
    public Categoria save(Categoria categoria){
        return categoriaRepository.save(categoria);
    }
}
