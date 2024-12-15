package pe.sia.presentation.controller.problemaController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.sia.persistence.entity.problema.Prioridad;
import pe.sia.presentation.dto.problemaDTO.PrioridadDTO;
import pe.sia.service.interfaces.PrioridadService;

@RestController
public class PrioridadController {

    private final PrioridadService prioridadService;

    public PrioridadController(PrioridadService prioridadService) {
        this.prioridadService = prioridadService;
    }

    @GetMapping("/adminuser/all-prioridad")
    public ResponseEntity<PrioridadDTO> getAllCategoria() {
        return ResponseEntity.ok(prioridadService.listAllPrioridad());
    }

    @PostMapping("/admin/prioridad-create")
    public ResponseEntity<PrioridadDTO> createPrioridad(@RequestBody Prioridad prioridad) {
        return ResponseEntity.ok(prioridadService.insertPrioridad(prioridad));
    }

    @PutMapping("/admin/prioridad-update/{id}")
    public ResponseEntity<PrioridadDTO> updatePrioridad(@PathVariable("id") Integer id, @RequestBody Prioridad prioridad) {
        return ResponseEntity.ok(prioridadService.updatePrioridad(id, prioridad));
    }

    @DeleteMapping("/admin/prioridad-delete/{id}")
    public ResponseEntity<PrioridadDTO> deletePrioridad(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(prioridadService.deletePrioridad(id));
    }

}
