package pe.sia.presentation.controller.falloIncidenciaController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.sia.presentation.dto.incienciasDTO.AuditoriaDTO;
import pe.sia.service.interfaces.AuditoriaService;

@RestController
public class AuditoriaController {

    private final AuditoriaService auditoriaService;

    public AuditoriaController(AuditoriaService auditoriaService) {
        this.auditoriaService = auditoriaService;
    }

    @GetMapping("/adminuser/audi-mante/{id}")
    public ResponseEntity<AuditoriaDTO> getAuditoriaMantenimiento(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(auditoriaService.getAuditoriaMantenimiento(id));
    }

    @PostMapping("/adminuser/insert-auditoria")
    public ResponseEntity<AuditoriaDTO> insertAuditoria(@RequestBody AuditoriaDTO auditoriaDTO) {
        return ResponseEntity.ok(auditoriaService.insertAuditoria(auditoriaDTO));
    }

    @PutMapping("/adminuser/update-auditoria/{id}")
    public ResponseEntity<AuditoriaDTO> delete(@PathVariable("id") Integer id, @RequestBody AuditoriaDTO auditoriaDTO ) {
        return ResponseEntity.ok(auditoriaService.updateAuditoria(id, auditoriaDTO));
    }

    @DeleteMapping("/adminuser/delete-auditoria/{id}")
    public ResponseEntity<AuditoriaDTO> deleteAuditoria(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(auditoriaService.deleteAuditoria(id));
    }

}
