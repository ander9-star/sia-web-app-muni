package pe.sia.presentation.controller.activosControllers;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import pe.sia.presentation.dto.activosDTO.HardwareDTO;
import pe.sia.service.interfaces.HardwareService;

@RestController
@RequestMapping("/sia/hardware")
public class HardwareController {

    private final HardwareService hardwareService;

    public HardwareController(HardwareService hardwareService) {
        this.hardwareService = hardwareService;
    }

    @PostMapping("/create")
    public ResponseEntity<HardwareDTO> createHardware(@RequestBody HardwareDTO hardwareDTO) {
        return ResponseEntity.ok(hardwareService.createHardware(hardwareDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HardwareDTO> updateHardware(@PathVariable Long id, @RequestBody HardwareDTO hardwareDTO) {
        return ResponseEntity.ok(hardwareService.updateHardware(id, hardwareDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHardware(@PathVariable Long id) {
        hardwareService.deleteHardware(id);
        return ResponseEntity.noContent().build();  
    }

    @GetMapping("/{id}")
    public ResponseEntity<HardwareDTO> getHardwareById(@PathVariable Long id) {
        return ResponseEntity.ok(hardwareService.getHardwareById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<HardwareDTO>> getAllHardware() {
        return ResponseEntity.ok(hardwareService.getAllHardware());
    }
 
}
