package pe.sia.presentation.controller.activosController;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.sia.presentation.dto.activosDTO.ActivoInformaticoDTO;
import pe.sia.service.interfaces.ActivoInformaticoService;

@RestController
@RequestMapping("sia/activoinformatico")
public class ActivoInformaticoController {
    
    private final ActivoInformaticoService activoInformaticoService;

    public ActivoInformaticoController(ActivoInformaticoService activoInformaticoService) {
        this.activoInformaticoService = activoInformaticoService;
    }

    @PostMapping("/create")
    public ResponseEntity<ActivoInformaticoDTO> createActivoInformatico(@RequestBody ActivoInformaticoDTO activoInformaticoDTO) {
        return ResponseEntity.ok(activoInformaticoService.createActivoInformatico(activoInformaticoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActivoInformaticoDTO> updateActivoInformatico(@PathVariable Integer id, @RequestBody ActivoInformaticoDTO activoInformaticoDTO) {
        return ResponseEntity.ok(activoInformaticoService.updateActivoInformatico(id, activoInformaticoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivoInformatico(@PathVariable Integer id) {
        activoInformaticoService.deleteActivoInformatico(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ActivoInformaticoDTO getActivoInformaticoById(@PathVariable Integer id) {
        return activoInformaticoService.getActivoInformaticoById(id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ActivoInformaticoDTO>> getAllActivoInformatico() {
        return ResponseEntity.ok(activoInformaticoService.getAllActivoInformatico());
    }

}
