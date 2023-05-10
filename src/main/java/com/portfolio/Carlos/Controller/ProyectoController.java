
package com.portfolio.Carlos.Controller;


import com.portfolio.Carlos.Dto.dtoProyecto;
import com.portfolio.Carlos.Entity.Proyectos;
import com.portfolio.Carlos.Security.Controller.Mensaje;
import com.portfolio.Carlos.Service.SProyectos;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("proyectos")
@CrossOrigin(origins = {"https://carlosalfredogomez1981.com/", "https://localhost:4200"})
public class ProyectoController {
   @Autowired
    SProyectos sproyectos; 
   
    @GetMapping("/lista")
    public ResponseEntity<List<Proyectos>> list(){
        List<Proyectos> list = sproyectos.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Proyectos> getById(@PathVariable("id")int id){
        if(!sproyectos.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.BAD_REQUEST);
        }
        
        Proyectos proyecto = sproyectos.getOne(id).get();
        return new ResponseEntity(proyecto, HttpStatus.OK);
    }
    
       @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoProyecto dtopro) {
        if (StringUtils.isBlank(dtopro.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (sproyectos.existsByNombre(dtopro.getNombre())) {
            return new ResponseEntity(new Mensaje("esa experiencia ya existe"), HttpStatus.BAD_REQUEST);
        }

        Proyectos proyecto = new Proyectos(dtopro.getNombre(),dtopro.getDescripcion(),dtopro.getUrlProyecto(),dtopro.getImgProyecto());
        sproyectos.save(proyecto);
        return new ResponseEntity(new Mensaje("experiencia agregada"), HttpStatus.OK);

    }
    
      @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!sproyectos.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        sproyectos.delete(id);
        return new ResponseEntity(new Mensaje("proyecto eliminado"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoProyecto dtoproyecto){
        if(!sproyectos.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        if(sproyectos.existsByNombre(dtoproyecto.getNombre()) && sproyectos.getByNombre(dtoproyecto.getNombre()).get().getId() != id){
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoproyecto.getNombre())){
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }
        
        Proyectos proyecto = sproyectos.getOne(id).get();
        
      proyecto.setNombre(dtoproyecto.getNombre());
      proyecto.setDescripcion(dtoproyecto.getDescripcion());
      proyecto.setUrlProyecto(dtoproyecto.getUrlProyecto());
      proyecto.setImgProyecto(dtoproyecto.getImgProyecto());
      
      
        
        
        sproyectos.save(proyecto);
        
        return new ResponseEntity(new Mensaje("Proyecto actualizada"), HttpStatus.OK);
    }
   
   
   
   
}
