package pe.sia.presentation.controller.falloIncidenciaController;

import org.springframework.http.ResponseEntity;
import pe.sia.presentation.dto.incienciasDTO.PrioridadDTO;
import pe.sia.service.interfaces.PrioridadService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
