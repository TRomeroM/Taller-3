package com.tromero.taller3.controller;


import com.tromero.taller3.model.Libro;
import com.tromero.taller3.service.LibroService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/libro")
public class LibroController {
    @Autowired
    private LibroService libroService;

    //Crear libro
    @Operation(summary ="Este post crear un libro", description = "Los parametros requeridos para crear un libro son 'autor', 'isbn' y 'titulo', el id del libro se genera automaticamente.")
    @PostMapping
    public Libro crearlibro(@RequestBody Libro libro){
        return libroService.crearLibro(libro);
    }

    // Obtner todos los libros
    @Operation(summary ="Este get obtiene un libro", description = "este método no requiere parametros")
    @GetMapping
    public List<Libro> obtenerTodosLosLibros(){
        return libroService.obtenerTodosLosLibros();
    }

    //Obtener libro por ID
    @Operation(summary ="Este get obtiene un libro por ID", description = "este método solo requiere id como parametro")
    @GetMapping ("/{id}")
    public ResponseEntity<Libro> obtenerLibroPorId(@PathVariable Long id ){
        Optional<Libro> libro=libroService.obtenerLibroPorId(id);
        return libro.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    //Actualizar un libro
    @Operation(summary ="Este put actualiza un libro", description = "este método actualiza todos los datos menos el ID")
    @PutMapping ("/{id}")
    public ResponseEntity<Libro> actualizarLibro(@PathVariable Long id,@RequestBody Libro libroDetails ){
        return ResponseEntity.ok(libroService.actualizarLibro(id, libroDetails));
    }

    //Eliminar un libro
    @Operation(summary ="Este delete elimina un libro", description = "este método solo requiere del ID como parámetro")
    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable Long id){
        libroService.eliminarLibro(id);
        return ResponseEntity.noContent().build();
    }
}
