
package com.portfolio.Carlos.Controller;



import com.portfolio.Carlos.Dto.dtoPersona;
import com.portfolio.Carlos.Entity.Persona;
import com.portfolio.Carlos.Security.Controller.Mensaje;
import com.portfolio.Carlos.Service.ImpPersonaService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personas")
@CrossOrigin(origins = {"https://carlosalfredogomez1981.com/", "https://localhost:4200"})
public class PersonaController {
    @Autowired
    ImpPersonaService personaService;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Persona>> list(){
        List<Persona> list = personaService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id")int id){
        if(!personaService.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.BAD_REQUEST);
        }
        
        Persona persona = personaService.getOne(id).get();
        return new ResponseEntity(persona, HttpStatus.OK);
    }
    
   
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoPersona dtopersona){
        if(!personaService.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        if(personaService.existsByNombre(dtopersona.getNombre()) && personaService.getByNombre(dtopersona.getNombre()).get().getId() != id){
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtopersona.getNombre())){
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }
        
        Persona persona = personaService.getOne(id).get();
        
        persona.setNombre(dtopersona.getNombre());
        persona.setApellido(dtopersona.getApellido());
        persona.setDescripcion(dtopersona.getDescripcion());
        persona.setImgBanner(dtopersona.getImgBanner());
        persona.setNacionalidad(dtopersona.getNacionalidad());
        persona.setDni(dtopersona.getDni());
        persona.setImgPerfil(dtopersona.getImgPerfil());
        persona.setMail(dtopersona.getMail());
        persona.setTelefono(dtopersona.getTelefono());
        persona.setFecha_nacimiento(dtopersona.getFecha_nacimiento());
        persona.setGithub(dtopersona.getGithub());
        persona.setInstagram(dtopersona.getInstagram());
        persona.setFacebook(dtopersona.getFacebook());
        persona.setWhatsapp(dtopersona.getWhatsapp());
        
        
        personaService.save(persona);
        
        return new ResponseEntity(new Mensaje("Persona actualizada"), HttpStatus.OK);
    }
   
}





 /*@DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!personaService.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        personaService.delete(id);
        return new ResponseEntity(new Mensaje("Educacion eliminada"), HttpStatus.OK);
    }*/
    
    /*@PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoEducacion dtoeducacion){
        if(StringUtils.isBlank(dtoeducacion.getNombreE())){
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(personaService.existsByNombreE(dtoeducacion.getNombreE())){
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        
        Educacion educacion = new Educacion(
                dtoeducacion.getNombreE(), dtoeducacion.getDescripcionE()
            );
        personaService.save(educacion);
        return new ResponseEntity(new Mensaje("Educacion creada"), HttpStatus.OK);
                
    }*/






/*
import com.portfolio.Carlos.Entity.Persona;
import com.portfolio.Carlos.Interface.IPersonaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin (origins = "http://localhost:4200")

public class PersonaController {
    
    
     @Autowired IPersonaService ipersonaService;
     
    @GetMapping("/personas/traer")
           public List<Persona> getPersona(){
        return ipersonaService.getPersona(); 
    }
    
    @PreAuthorize("hasRole('ADMIN')")       
    @PostMapping ("/personas/crear")
    public String createPersona(@RequestBody Persona persona){
        ipersonaService.savePersona(persona);
        return "la persona fué creada con éxito";
    }
    
    @PreAuthorize("hasRole('ADMIN')") 
    @DeleteMapping ("/personas/borrar/{id}")
    public String deletePersona(@PathVariable Long id){
        ipersonaService.deletePersona(id);
        return "la persona fué eliminada corectamente";
    }
    
    @PreAuthorize("hasRole('ADMIN')") 
    @PutMapping("/personas/editar/{id}")
    
    public Persona editPersona (@PathVariable Long id,
            @RequestParam("nombre") String nuevoNombre,
            @RequestParam("apellido") String nuevoApellido,
            @RequestParam("nacionalidad") String nuevoNacionalidad,
            @RequestParam("fecha_nacimiento") String nuevoFechaNacimiento,
            @RequestParam("dni") String nuevoDni,
            @RequestParam("telefono") String nuevoTelefono,
            @RequestParam("mail") String nuevoMail,
            @RequestParam("descripcion") String nuevoDescripcion,
            @RequestParam("imgBanner") String nuevoImgBanner,
            @RequestParam("imgPerfil") String nuevoImgPerfil)
    {
        
    Persona persona = ipersonaService.findPersona(id);
    
    
    persona.setNombre(nuevoNombre);
    persona.setApellido(nuevoApellido);
    persona.setNacionalidad(nuevoNacionalidad);
    persona.setFecha_nacimiento(nuevoFechaNacimiento);
    persona.setDni(nuevoDni);
    persona.setTelefono(nuevoTelefono);
    persona.setMail(nuevoMail);
    persona.setDescripcion(nuevoDescripcion);
    persona.setImgBanner(nuevoImgBanner);
    persona.setImgPerfil(nuevoImgPerfil);
    
    ipersonaService.savePersona(persona);
    return persona;
    }
    
    @GetMapping("/personas/traer/perfil")
    public Persona findPersona(){
        return ipersonaService.findPersona(Long.MIN_VALUE);
    }
}
*/