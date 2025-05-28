package com.ecommerce.Ecommerce.services;

import com.ecommerce.Ecommerce.dto.CategoriaRequest;
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
        return categoriaRepository.findById(id).orElseThrow(() -> new RuntimeException("Categoría no encontrada con id: " + id));
    }
    
    public Categoria save(Categoria categoria){
        return categoriaRepository.save(categoria);
    }

    public Categoria update(Long id, CategoriaRequest categoriaRequest){

        Categoria categoria = this.findById(id);

        categoria.setNombre(categoriaRequest.getNombre());
        categoria.setDescripcion(categoriaRequest.getDescripcion());


        return categoriaRepository.save(categoria);
    }

    public Boolean delete(Long id){
        Categoria categoria = this.findById(id);
        categoriaRepository.delete(categoria);
        return true;
    }
}
