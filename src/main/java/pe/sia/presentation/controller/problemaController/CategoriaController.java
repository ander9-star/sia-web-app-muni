package pe.sia.presentation.controller.falloIncidenciaController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.sia.persistence.entity.problema.Categoria;
import pe.sia.presentation.dto.incienciasDTO.CategoriaDTO;
import pe.sia.service.interfaces.CategoriaService;

@RestController
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping("/admin/categoria-create")
    public ResponseEntity<CategoriaDTO> createCategoria(@RequestBody Categoria categoria) {
        return ResponseEntity.ok(categoriaService.insertCategoria(categoria));
    }

    @PutMapping("/admin/categoria-update/{id}")
    public ResponseEntity<CategoriaDTO> updateCategoria(@PathVariable("id") Integer id, @RequestBody Categoria categoria) {
        return ResponseEntity.ok(categoriaService.updateCategoria(id, categoria));
    }

    @DeleteMapping("/admin/categoria-delete/{id}")
    public ResponseEntity<CategoriaDTO> deleteCategoria(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(categoriaService.deleteCategoria(id));
    }

    @GetMapping("/adminuser/all-categoria")
    public ResponseEntity<CategoriaDTO> getAllCategoria() {
        return ResponseEntity.ok(categoriaService.listarAllCategoria());
    }

}
