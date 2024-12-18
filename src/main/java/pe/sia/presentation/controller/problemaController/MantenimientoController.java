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

    @GetMapping("/soporte-tecnico/verificar-dp/{idDetalleProblema}")
    public ResponseEntity<MantenimientoDTO> getMantenimientoDetalleProblema(@PathVariable("idDetalleProblema") Integer idDetalleProblema) {
        return ResponseEntity.ok(mantenimientoService.getMantenimientoDetalleProblema(idDetalleProblema));
    }

    @GetMapping("/adminuser/all-mantenimiento/{idUsuario}/{esAdmin}")
    public ResponseEntity<MantenimientoDTO> getDataMantenimiento(@PathVariable("idUsuario") Integer idUsuario, @PathVariable("esAdmin") Boolean esAdmin) {
        return ResponseEntity.ok(mantenimientoService.getDataMantenimiento(idUsuario, esAdmin));
    }

    @GetMapping("/adminuser/sin-mantenimiento")
    public ResponseEntity<MantenimientoDTO> getFISinMantenimiento() {
        return ResponseEntity.ok(mantenimientoService.getFISinMantenimiento());
    }

    @GetMapping("/soporte-tecnico/mantenimiento-hoy-ayer")
    public ResponseEntity<Map<String, Object>> getTotalManenimientoHoyAyer() {
        return ResponseEntity.ok(mantenimientoService.getTotalManenimientoHoyAyer());
    }

    @GetMapping("/soporte-tecnico/mantenimiento-total-vencida")
    public ResponseEntity<Map<String, Object>> getTotalVencidaMantenimiento() {
        return ResponseEntity.ok(mantenimientoService.getTotalVencidaMantenimiento());
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
