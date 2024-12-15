package pe.sia.presentation.controller.activosController;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import pe.sia.presentation.dto.activosDTO.InfraestructuraRedDTO;
import pe.sia.service.interfaces.InfraestructuraRedService;

@RestController
public class InfraestructuraRedController {

    private final InfraestructuraRedService redService;

    public InfraestructuraRedController(InfraestructuraRedService redService) {
        this.redService = redService;
    }

    @PostMapping("admin/red-create")
    public ResponseEntity<InfraestructuraRedDTO> createRed(@RequestBody InfraestructuraRedDTO redDTO) {
        return ResponseEntity.ok(redService.createRed(redDTO));
    }

    @PutMapping("admin/red-update/{id}")
    public ResponseEntity<InfraestructuraRedDTO> updateRed(@PathVariable Integer id, @RequestBody InfraestructuraRedDTO redDTO) {
        return ResponseEntity.ok(redService.updateRed(id, redDTO));
    }

    @DeleteMapping("admin/red-delete/{id}")
    public ResponseEntity<Void> deleteRed(@PathVariable Integer id) {
        redService.deleteRed(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("admin/red-by-id/{id}")
    public ResponseEntity<InfraestructuraRedDTO> getRedById(@PathVariable Integer id) {
        return ResponseEntity.ok(redService.getRedById(id));
    }
    
    @GetMapping("admin/red-all")
    public ResponseEntity<List<InfraestructuraRedDTO>> getAllRedes() {
        return ResponseEntity.ok(redService.getAllRedes());
    }

    @GetMapping("adminuser/red-data")
    public ResponseEntity<InfraestructuraRedDTO> getDataRed() {
        return ResponseEntity.ok(redService.getFIRed());
    }
    
}
