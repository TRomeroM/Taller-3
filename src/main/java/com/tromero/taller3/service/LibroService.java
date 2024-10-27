package com.tromero.taller3.service;


import com.tromero.taller3.model.Libro;
import com.tromero.taller3.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    //Crear un libro
    public Libro crearLibro(Libro libro){
        return libroRepository.save(libro);
    }

    //Obtener todos los libros
    public List<Libro> obtenerTodosLosLibros(){

        return libroRepository.findAll();
    }

    //Obtener libro por ID
    public Optional<Libro> obtenerLibroPorId(Long id) {
        return libroRepository.findById(id);
    }

    //Actualizar un libro
    public Libro actualizarLibro(Long id,Libro libroDetails){
        Libro libro= libroRepository.findById(id).orElseThrow();
        libro.setAutor(libroDetails.getAutor());
        libro.setTitulo(libroDetails.getTitulo());
        libro.setIsbn(libroDetails.getIsbn());
        return libroRepository.save(libro);
    }

    //Eliminar un libro
    public void eliminarLibro(Long id){
        libroRepository.deleteById(id);
    }
}
