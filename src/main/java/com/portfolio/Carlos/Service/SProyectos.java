/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.Carlos.Service;


import com.portfolio.Carlos.Entity.Proyectos;
import com.portfolio.Carlos.Repository.RProyectos;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author CARLOSCASA
 */
@Service
@Transactional
public class SProyectos {
    @Autowired
    RProyectos rproyectos;
    
    public List<Proyectos> list(){
         return rproyectos.findAll();
     }
     
     public Optional<Proyectos> getOne(int id){
         return rproyectos.findById(id);
     }
     
     public Optional<Proyectos> getByNombre(String nombre){
         return rproyectos.findByNombre(nombre);
     }
     
     public void save(Proyectos proyecto){
         rproyectos.save(proyecto);
     }
     
     public void delete(int id){
         rproyectos.deleteById(id);
     }
     
     public boolean existsById(int id){
         return rproyectos.existsById(id);
     }
     
     public boolean existsByNombre(String nombre){
         return rproyectos.existsByNombre(nombre);
     }

}
