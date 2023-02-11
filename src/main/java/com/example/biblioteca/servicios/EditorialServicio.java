
package com.example.biblioteca.servicios;

import com.example.biblioteca.entidades.Editorial;
import com.example.biblioteca.excepciones.MiException;
import com.example.biblioteca.repositorios.EditorialRepositorio;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;


public class EditorialServicio {
    
    @Autowired
    EditorialRepositorio editorialRepositorio;
    
    @Transactional
    public void crearEditorial(String nombre) throws MiException{
        
        validar(nombre);
        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);
        
        editorialRepositorio.save(editorial);
    }
    
    @Transactional
    public void modificarEditorial(String nombre, String id) throws MiException{
        validar(nombre);
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        
        if(respuesta.isPresent()){
            Editorial editorial = respuesta.get();
            editorial.setNombre(nombre);
            
            editorialRepositorio.save(editorial);
            
        }
        
        
    }
     private void validar(String nombre)throws MiException{
        if(nombre.isEmpty()||nombre== null){
            throw new MiException("el nombre no puede ser nulo o vacío");
        }
    }
    
    
    
}
