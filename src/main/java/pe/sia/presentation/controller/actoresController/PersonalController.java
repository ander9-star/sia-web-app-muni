package pe.sia.presentation.controller.actoresController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.sia.presentation.dto.actoresDTO.PersonalDTO;
import pe.sia.service.interfaces.PersonalService;

@RestController
public class PersonalController {

    private final PersonalService personalService;

    public PersonalController(PersonalService personalService) {
        this.personalService = personalService;
    }

    @GetMapping("/admin/personal-mantenimiento")
    public ResponseEntity<PersonalDTO> getPersonalMantenimiento() {
        return ResponseEntity.ok(personalService.getPersonalMantenimiento());
    }

    @GetMapping("/adminuser/personal-short")
    public ResponseEntity<PersonalDTO> getIdNombrepersonal() {
        return ResponseEntity.ok(personalService.getIdNombrepersonal());
    }

    @GetMapping("/admin/personal-full")
    public ResponseEntity<PersonalDTO> getFullDataPersonal() {
        return ResponseEntity.ok(personalService.getFullDataPersonal());
    }

    @PutMapping("/admin/personal-update/{id}")
    public ResponseEntity<PersonalDTO> updatePersonaL(@PathVariable("id") Integer idPersonal, @RequestBody PersonalDTO personalDTO) {
        return ResponseEntity.ok(personalService.updatePersonal(idPersonal, personalDTO));
    }

    @DeleteMapping("/admin/personal-delete/{id}")
    public ResponseEntity<PersonalDTO> deletePersonal(@PathVariable("id") Integer idPersonal) {
        return ResponseEntity.ok(personalService.deletePersonal(idPersonal));
    }

    @PostMapping("/admin/personal-insert")
    public ResponseEntity<PersonalDTO> insertPersonal(@RequestBody PersonalDTO personalDTO) {
        return ResponseEntity.ok(personalService.insertPersonal(personalDTO));
    }
}
