package pe.sia.presentation.controller.problemaController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.sia.presentation.dto.problemaDTO.AuditoriaDTO;
import pe.sia.service.interfaces.AuditoriaService;
import java.util.Map;

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

    @GetMapping("/adminuser/auditoria-full/{idUsuario}/{esAdmin}")
    public ResponseEntity<AuditoriaDTO> getDataFullAuditoria(@PathVariable("idUsuario") Integer idUsuario, @PathVariable("esAdmin") Boolean esAdmin) {
        return ResponseEntity.ok(auditoriaService.getDataFullAuditoria(idUsuario, esAdmin));
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

    @GetMapping("/soporte-tecnico/auditoria-incidencia-hoy-total")
    public ResponseEntity<Map<String, Object>> getTotalAuditoriaHoyTotal() {
        return ResponseEntity.ok(auditoriaService.getTotalAuditoriaHoyTotal());
    }

}
