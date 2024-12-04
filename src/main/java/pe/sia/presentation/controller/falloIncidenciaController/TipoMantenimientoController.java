package pe.sia.presentation.controller.falloIncidenciaController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.sia.presentation.dto.incienciasDTO.TipoMantenimientoDTO;
import pe.sia.service.interfaces.TipoMantenimientoService;

@RestController
public class TipoMantenimientoController {

    private final TipoMantenimientoService tipoMantenimientoService;

    public TipoMantenimientoController(TipoMantenimientoService tipoMantenimientoService) {
        this.tipoMantenimientoService = tipoMantenimientoService;
    }

    @GetMapping("/adminuser/short-tp")
    public ResponseEntity<TipoMantenimientoDTO> getAllCategoria() {
        return ResponseEntity.ok(tipoMantenimientoService.getDataShortTipoMantenimiento());
    }
}
