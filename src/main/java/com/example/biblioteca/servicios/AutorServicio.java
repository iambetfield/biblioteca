
package com.example.biblioteca.servicios;

import com.example.biblioteca.entidades.Autor;
import com.example.biblioteca.excepciones.MiException;
import com.example.biblioteca.repositorios.AutorRepositorio;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorServicio {
    
    @Autowired
    AutorRepositorio autorRepositorio;
    
    @Transactional
    public void crearAutor(String nombre) throws MiException{
        
        validar(nombre);
        
        Autor autor = new Autor();
        autor.setNombre(nombre);
        
        autorRepositorio.save(autor);
    }
    
    
    public void modificarAutor(String nombre, String id) throws MiException{
        
        validar(nombre);
        Optional<Autor> respuesta = autorRepositorio.findById(id);
        
        if(respuesta.isPresent()){
            
            Autor autor = respuesta.get();
            autor.setNombre(nombre);
            
            autorRepositorio.save(autor);
        }
        
    }
    
    private void validar(String nombre)throws MiException{
        if(nombre.isEmpty()||nombre== null){
            throw new MiException("el nombre no puede ser nulo o vacío");
        }
    }
}
