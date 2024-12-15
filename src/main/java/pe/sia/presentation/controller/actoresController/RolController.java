package pe.sia.presentation.controller.actoresController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.sia.persistence.entity.actores.Rol;
import pe.sia.presentation.dto.actoresDTO.RolDTO;
import pe.sia.service.interfaces.RolService;

@RestController
public class RolController {

    private final RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @PostMapping("/admin/rol-create")
    public ResponseEntity<RolDTO> createRol(@RequestBody Rol rol) {
        return ResponseEntity.ok(rolService.insertRol(rol));
    }

    @PutMapping("/admin/rol-update/{idRol}")
    public ResponseEntity<RolDTO> updateRol(@PathVariable("idRol") Integer idRol, @RequestBody Rol rol) {
        return ResponseEntity.ok(rolService.updateRol(idRol, rol));
    }

    @DeleteMapping("/admin/rol-delete/{idRol}")
    public ResponseEntity<RolDTO> deleteRol(@PathVariable("idRol") Integer idRol) {
        return ResponseEntity.ok(rolService.deleteRol(idRol));
    }

    @GetMapping("/admin/data-rol")
    public ResponseEntity<RolDTO> getDataRol() {
        return  ResponseEntity.ok(rolService.getDataRol());
    }
}
