package pe.sia.presentation.controller.activosController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import pe.sia.presentation.dto.activosDTO.SoftwareDTO;
import pe.sia.service.interfaces.SoftwareService;

@RestController
public class SoftwareController {
    
    private final SoftwareService softwareService;

    public SoftwareController(SoftwareService softwareService) {
        this.softwareService = softwareService;
    }

    @PostMapping("/admin/software-create")
    public ResponseEntity<SoftwareDTO> createSoftware(@RequestBody SoftwareDTO softwareDTO) {
        return ResponseEntity.ok(softwareService.createSoftware(softwareDTO));
    }

    @PutMapping("/admin/software-update/{id}")
    public ResponseEntity<SoftwareDTO> updateSoftware(@PathVariable Integer id, @RequestBody SoftwareDTO softwareDTO) {
        return ResponseEntity.ok(softwareService.updateSoftware(id, softwareDTO));
    }

    @DeleteMapping("/admin/software-update/{id}")
    public ResponseEntity<Void> deleteSoftware(@PathVariable Integer id) {
        softwareService.deleteSoftware(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/admin/software-by-id/{id}")
    public ResponseEntity<SoftwareDTO> getSotfwareById(@PathVariable Integer id) {
        return ResponseEntity.ok(softwareService.getSoftwareById(id));
    }

    @GetMapping("/admin/software-all")
    public ResponseEntity<List<SoftwareDTO>> getAllSoftwares() {
        return ResponseEntity.ok(softwareService.getAllSoftwares());
    }

    @GetMapping("/adminuser/software-data")
    public ResponseEntity<SoftwareDTO> getFISoftware() {
        return ResponseEntity.ok(softwareService.getFISoftware());
    }

}
