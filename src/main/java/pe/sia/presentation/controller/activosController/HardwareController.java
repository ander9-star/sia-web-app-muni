package pe.sia.presentation.controller.activosController;

import java.util.List;

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
public class HardwareController {

    private final HardwareService hardwareService;

    public HardwareController(HardwareService hardwareService) {
        this.hardwareService = hardwareService;
    }

    @PostMapping("/admin/hardware-create")
    public ResponseEntity<HardwareDTO> createHardware(@RequestBody HardwareDTO hardwareDTO) {
        return ResponseEntity.ok(hardwareService.createHardware(hardwareDTO));
    }

    @PutMapping("/admin/hardware-update/{id}")
    public ResponseEntity<HardwareDTO> updateHardware(@PathVariable Integer id, @RequestBody HardwareDTO hardwareDTO) {
        return ResponseEntity.ok(hardwareService.updateHardware(id, hardwareDTO));
    }

    @DeleteMapping("/admin/hardware-delete/{id}")
    public ResponseEntity<Void> deleteHardware(@PathVariable Integer id) {
        hardwareService.deleteHardware(id);
        return ResponseEntity.noContent().build();  
    }

    @GetMapping("/admin/hardware-by-id/{id}")
    public ResponseEntity<HardwareDTO> getHardwareById(@PathVariable Integer id) {
        return ResponseEntity.ok(hardwareService.getHardwareById(id));
    }

    @GetMapping("/admin/hardware-all")
    public ResponseEntity<List<HardwareDTO>> getAllHardware() {
        return ResponseEntity.ok(hardwareService.getAllHardware());
    }

    @GetMapping("/adminuser/hardware-data")
    public ResponseEntity<HardwareDTO> getFIPorHardware() {
        return ResponseEntity.ok(hardwareService.getFIPorHardware());
    }
 
}
