package pe.sia.presentation.controller.falloIncidenciaController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.sia.presentation.dto.incienciasDTO.MantenimientoDTO;
import pe.sia.service.interfaces.MantenimientoService;

@RestController
public class MantenimientoController {

    private final MantenimientoService mantenimientoService;

    public MantenimientoController(MantenimientoService mantenimientoService) {
        this.mantenimientoService = mantenimientoService;
    }

    @PostMapping("/adminuser/insert-mantenimiento")
    public ResponseEntity<MantenimientoDTO> insertarMantenimiento(@RequestBody MantenimientoDTO mantenimientoDTO) {
        return ResponseEntity.ok(mantenimientoService.insertMantenimiento(mantenimientoDTO));
    }

    @GetMapping("/soporte-tecnico/verificar-fallo/{idFallo}")
    public ResponseEntity<MantenimientoDTO> getMantenimientoFallo(@PathVariable("idFallo") Integer idFallo) {
        return ResponseEntity.ok(mantenimientoService.getMantenimientoFallo(idFallo));
    }

    @GetMapping("/adminuser/all-mantenimiento")
    public ResponseEntity<MantenimientoDTO> getDataMantenimiento() {
        return ResponseEntity.ok(mantenimientoService.getDataMantenimiento());
    }

    @GetMapping("/adminuser/sin-mantenimiento")
    public ResponseEntity<MantenimientoDTO> getFISinMantenimiento() {
        return ResponseEntity.ok(mantenimientoService.getFISinMantenimiento());
    }

    @PutMapping("/adminuser/update-mantenimiento/{id}")
    public ResponseEntity<MantenimientoDTO> updateMantenimiento(@PathVariable("id") Integer id, @RequestBody MantenimientoDTO item) {
        return ResponseEntity.ok(mantenimientoService.updateMantenimiento(id, item));
    }

    @DeleteMapping("/adminuser/delete-mantenimiento/{id}")
    public ResponseEntity<MantenimientoDTO> deleteMantenimiento(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(mantenimientoService.deleteMantenimiento(id));
    }


}
