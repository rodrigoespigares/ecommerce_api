package com.ecommerce.Ecommerce.controllers;

import com.ecommerce.Ecommerce.dto.CategoriaRequest;
import com.ecommerce.Ecommerce.models.Categoria;
import com.ecommerce.Ecommerce.models.User;
import com.ecommerce.Ecommerce.services.CategoriaService;
import com.ecommerce.Ecommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<Map<String, Object>> index(){
        Map<String, Object> response = new HashMap<>();

        List<Categoria> categorias = categoriaService.findAll();

        response.put("status", "success");
        response.put("data", categorias);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> show(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        try{
            Categoria categoria = categoriaService.findById(id);
            response.put("status", "success");
            response.put("data", categoria);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("data", "Parece que ha ocurrido un error buscando la categoría.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PostMapping()
    public ResponseEntity<Map<String, Object>> create(@RequestBody CategoriaRequest categoria_dto){
        Map<String, Object> response = new HashMap<>();

        Optional<User> user = userService.findById(19L);

        if (user.isEmpty()) {
            response.put("status", "error");
            response.put("message", "Usuario no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        Categoria categoria = new Categoria();
        categoria.setNombre(categoria_dto.getNombre());
        categoria.setDescripcion(categoria_dto.getDescripcion());
        categoria.setUsuario(user.get());

        Categoria nuevaCategoria = categoriaService.save(categoria);

        response.put("status", "success");
        response.put("data", nuevaCategoria);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> edit(@PathVariable Long id, @RequestBody CategoriaRequest categoriaRequest){
        Map<String, Object> response = new HashMap<>();

        try {
            Categoria categoria = categoriaService.update(id, categoriaRequest);

            response.put("status", "success");
            response.put("data", categoria);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Categoría no encontrada");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long id){
        Map<String, String> response = new HashMap<>();

        try{

            categoriaService.delete(id);
            response.put("status", "success");
            response.put("message", "Categoría borrada");
            return ResponseEntity.status(HttpStatus.OK).body(response);

        }catch(Exception e){
            response.put("status", "error");
            response.put("message", "Categoría no encontrada");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }


    }
}
