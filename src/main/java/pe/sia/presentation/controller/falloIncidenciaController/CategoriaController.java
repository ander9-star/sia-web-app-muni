package pe.sia.presentation.controller.falloIncidenciaController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.sia.presentation.dto.incienciasDTO.CategoriaDTO;
import pe.sia.service.interfaces.CategoriaService;

@RestController
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/adminuser/all-categoria")
    public ResponseEntity<CategoriaDTO> getAllCategoria() {
        return ResponseEntity.ok(categoriaService.listarAllCategoria());
    }

}
