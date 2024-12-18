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
import pe.sia.presentation.dto.activosDTO.ActivoMaestroDTO;
import pe.sia.service.interfaces.ActivoMaestroService;

@RestController
@RequestMapping("/sia/tipoactivo")
public class TipoActivoController {

    private final ActivoMaestroService tipoActivoService;

    public TipoActivoController(ActivoMaestroService tipoActivoService) {
        this.tipoActivoService = tipoActivoService;
    }

    // 1. Petición para crear un tipo de activo
    @PostMapping("/create")
    public ResponseEntity<ActivoMaestroDTO> createTipoActivo(@RequestBody ActivoMaestroDTO tipoActivoDTO) {
        return ResponseEntity.ok(tipoActivoService.createTipoActivo(tipoActivoDTO));
    }

    // 2. Petición para actualizar un tipo de activo
    @PutMapping("/{id}")
    public ResponseEntity<ActivoMaestroDTO> updateTipoActivo(@PathVariable Integer id, @RequestBody ActivoMaestroDTO tipoActivoDTO) {
        return ResponseEntity.ok(tipoActivoService.updateTipoActivo(id, tipoActivoDTO));
    }

    // 3. Petición para eliminar un tipo de activo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTipoActivo(@PathVariable Integer id) {
        tipoActivoService.deleteTipoActivo(id);
        return ResponseEntity.noContent().build();
    }

    // 4. Buscar un tipo de activo por id
    @GetMapping("/{id}")
    public ResponseEntity<ActivoMaestroDTO> getTipoActivoById(@PathVariable Integer id) {
        return ResponseEntity.ok(tipoActivoService.getTipoActivoById(id));
    }

    // 5. Obtener todos los tipos de activo
    @GetMapping("/all")
    public ResponseEntity<List<ActivoMaestroDTO>> getAllTipoActivo()  {
        return ResponseEntity.ok(tipoActivoService.getAllTipoActivo());
    }
}
