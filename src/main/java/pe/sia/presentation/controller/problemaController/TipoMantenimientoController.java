package pe.sia.presentation.controller.falloIncidenciaController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.sia.persistence.entity.problema.TipoMantenimiento;
import pe.sia.presentation.dto.incienciasDTO.TipoMantenimientoDTO;
import pe.sia.service.interfaces.TipoMantenimientoService;

@RestController
public class TipoMantenimientoController {

    private final TipoMantenimientoService tipoMantenimientoService;

    public TipoMantenimientoController(TipoMantenimientoService tipoMantenimientoService) {
        this.tipoMantenimientoService = tipoMantenimientoService;
    }

    @GetMapping("/admin/tp-data")
    public ResponseEntity<TipoMantenimientoDTO> getAllTipoMantenimiento() {
        return ResponseEntity.ok(tipoMantenimientoService.listAllTipoMantenimiento());
    }

    @PostMapping("/admin/tp-create")
    public ResponseEntity<TipoMantenimientoDTO> createTipoMantenimiento(@RequestBody TipoMantenimiento tipoMantenimiento) {
        return ResponseEntity.ok(tipoMantenimientoService.insertTipoMantenimiento(tipoMantenimiento));
    }

    @PutMapping("/admin/tp-update/{id}")
    public ResponseEntity<TipoMantenimientoDTO> updateTipoMantenimiento(@PathVariable("id") Integer id, @RequestBody TipoMantenimiento tipoMantenimiento) {
        return ResponseEntity.ok(tipoMantenimientoService.updateTipoMantenimiento(id, tipoMantenimiento));
    }

    @DeleteMapping("/admin/tp-delete/{id}")
    public ResponseEntity<TipoMantenimientoDTO> deleteTipoMantenimiento(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(tipoMantenimientoService.deleteTipoMantenimiento(id));
    }

    @GetMapping("/adminuser/short-tp")
    public ResponseEntity<TipoMantenimientoDTO> getAllCategoria() {
        return ResponseEntity.ok(tipoMantenimientoService.getDataShortTipoMantenimiento());
    }
}
