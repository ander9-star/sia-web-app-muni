package pe.sia.presentation.controller.activosControllers;

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

import pe.sia.presentation.dto.activosDTO.RedDTO;
import pe.sia.service.interfaces.RedService;

@RestController
@RequestMapping("/sia/red")
public class RedController {

    private final RedService redService;

    public RedController(RedService redService) {
        this.redService = redService;
    }

    @PostMapping("/create")
    public ResponseEntity<RedDTO> createRed(@RequestBody RedDTO redDTO) {
        return ResponseEntity.ok(redService.createRed(redDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RedDTO> updateRed(@PathVariable Long id, @RequestBody RedDTO redDTO) {
        return ResponseEntity.ok(redService.updateRed(id, redDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRed(@PathVariable Long id) {
        redService.deleteRed(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RedDTO> getRedById(@PathVariable Long id) {
        return ResponseEntity.ok(redService.getRedById(id));
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<RedDTO>> getAllRedes() {
        return ResponseEntity.ok(redService.getAllRedes());
    }
    
}
