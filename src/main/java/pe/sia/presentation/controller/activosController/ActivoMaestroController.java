package pe.sia.presentation.controller.activosController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.sia.presentation.dto.activosDTO.ActivoMaestroDTO;
import pe.sia.service.interfaces.ActivoMaestroService;

@RestController
public class ActivoMaestroController {

    private final ActivoMaestroService activoMaestroService;

    public ActivoMaestroController(ActivoMaestroService activoMaestroService) {
        this.activoMaestroService = activoMaestroService;
    }

    @GetMapping("/adminuser/top-5-activos")
    public ResponseEntity<ActivoMaestroDTO> getTop5ActivosIncidencias() {
        return ResponseEntity.ok(activoMaestroService.getTop5ActivosIncidencias());
    }

    @GetMapping("/adminuser/activos-sin-problema")
    public ResponseEntity<ActivoMaestroDTO> getActivoMaestroSinProblemaGeneral() {
        return ResponseEntity.ok(activoMaestroService.getActivoMaestroSinProblemaGeneral());
    }

}