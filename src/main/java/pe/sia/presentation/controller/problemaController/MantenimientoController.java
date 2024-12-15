package pe.sia.presentation.controller.problemaController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.sia.presentation.dto.problemaDTO.MantenimientoDTO;
import pe.sia.service.interfaces.MantenimientoService;

import java.util.Map;

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

    @GetMapping("/soporte-tecnico/mantenimiento-incidencia-hoy-ayer")
    public ResponseEntity<Map<String, Object>> getTotalManenimientoHoyAyer() {
        return ResponseEntity.ok(mantenimientoService.getTotalManenimientoHoyAyer());
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
