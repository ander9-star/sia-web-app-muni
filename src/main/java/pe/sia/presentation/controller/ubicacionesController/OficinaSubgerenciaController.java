package pe.sia.presentation.controller.ubicacionesController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.sia.presentation.dto.ubicacionDTO.OficinaSubgerenciaDTO;
import pe.sia.service.interfaces.OficinaSubgerenciaService;

@RestController
public class OficinaSubgerenciaController {

    private final OficinaSubgerenciaService oficinaSubgerenciaService;

    public OficinaSubgerenciaController(OficinaSubgerenciaService oficinaSubgerenciaService) {
        this.oficinaSubgerenciaService = oficinaSubgerenciaService;
    }

    @GetMapping("/adminuser/profile-oficina")
    public ResponseEntity<OficinaSubgerenciaDTO> getDataProfileOficina() {
        return ResponseEntity.ok(oficinaSubgerenciaService.getDataForProfile());
    }

    @GetMapping("/adminuser/oficina-data")
    public ResponseEntity<OficinaSubgerenciaDTO> getDataOficina() {
        return ResponseEntity.ok(oficinaSubgerenciaService.getFIPorOficina());
    }
}
