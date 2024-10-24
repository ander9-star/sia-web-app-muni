package pe.sia.presentation.controller.activosControllers;

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

import pe.sia.presentation.dto.activosDTO.TipoRedDTO;
import pe.sia.service.interfaces.TipoRedService;

@RestController
@RequestMapping("/sia/tipored")
public class TipoRedController {

    private final TipoRedService tipoRedService;

    public TipoRedController(TipoRedService tipoRedService) {
        this.tipoRedService = tipoRedService;
    }

    // 1. Petición para crear un tipo de red
    @PostMapping("/create")
    public ResponseEntity<TipoRedDTO> createTipoRed(@RequestBody TipoRedDTO tipoRedDTO) {
        return ResponseEntity.ok(tipoRedService.createTipoRed(tipoRedDTO));
    }

    // 2. Petición para actualizar un tipo de red
    @PutMapping("/{id}")
    public ResponseEntity<TipoRedDTO> updateTipoRed(@PathVariable Integer id, @RequestBody TipoRedDTO tipoRedDTO) {
        return ResponseEntity.ok(tipoRedService.updateTipoRed(id, tipoRedDTO));
    }

    // 3. Petición para eliminar un tipo de red
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTipoRed(@PathVariable Integer id) {
        tipoRedService.deleteTipoRed(id);
        return ResponseEntity.noContent().build();
    }

    // 4. Buscar un tipo de red por id
    @GetMapping("/{id}")
    public ResponseEntity<TipoRedDTO> getTipoRedById(@PathVariable Integer id) {
        return ResponseEntity.ok(tipoRedService.getTipoRedById(id));
    }

    // 5. Obtener todos los tipos de red
    @GetMapping("/all")
    public ResponseEntity<List<TipoRedDTO>> getAllTipoRed()  {
        return ResponseEntity.ok(tipoRedService.getAllTipoRed());
    }

}
