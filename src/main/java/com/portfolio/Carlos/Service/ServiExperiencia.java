
package com.portfolio.Carlos.Service;

import com.portfolio.Carlos.Entity.Experiencia;
import com.portfolio.Carlos.Repository.RExperiencia;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service //indica que es un servicio
@Transactional //mantiene la persistencia en la base de datos 
public class ServiExperiencia {
    @Autowired //inserta el repositorio  lo inyecta
    RExperiencia rExperiencia;
    
    public List<Experiencia> list () {
        return rExperiencia.findAll();
    }
     public Optional<Experiencia> getOne (int id){
        return rExperiencia.findById(id);
     }  

     public Optional<Experiencia> getByNombreExperiencia(String nombreExperiencia){
         return rExperiencia.findByNombreExperiencia(nombreExperiencia);
     }
     
     public void save(Experiencia expe){
         rExperiencia.save(expe);
     }
     public void delete(int id){
         rExperiencia.deleteById(id);
              }
     
     public boolean existByid(int id){
         return rExperiencia.existsById(id);
     }
     
     public boolean existsByNombreExperiencia (String nombreExperiencia){
         return rExperiencia.existsByNombreExperiencia(nombreExperiencia);
     };
}
