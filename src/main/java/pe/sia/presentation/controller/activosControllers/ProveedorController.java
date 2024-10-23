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

import pe.sia.presentation.dto.activosDTO.ProveedorDTO;
import pe.sia.service.interfaces.ProveedorService;

/*
 * @author villata carnero jhon
 */

@RestController
@RequestMapping("/sia/proveedor")
public class ProveedorController {

    private final ProveedorService proveedorService;

    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    // 1. peticion para crear un proveedor
    @PostMapping("/create")
    public ResponseEntity<ProveedorDTO> createProveedor(@RequestBody ProveedorDTO proveedorDTO) {
        return ResponseEntity.ok(proveedorService.createProveedor(proveedorDTO));
    }

    // 2. peticion para actualizar un proveedor
    @PutMapping("/{id}")
    public ResponseEntity<ProveedorDTO> updateProveedor(@PathVariable Long id, @RequestBody ProveedorDTO proveedorDTO) {
        return ResponseEntity.ok(proveedorService.updateProveedor(id, proveedorDTO));
    }

    // 3. peticion para eliminar un proveedor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProveedor(@PathVariable Long id) {
        proveedorService.deleteProveedor(id);
        return ResponseEntity.noContent().build();
    }

    // 4. buscar un proveedor por id
    @GetMapping("/{id}")
    public ResponseEntity<ProveedorDTO> getProveedorById(@PathVariable Long id) {
        return ResponseEntity.ok(proveedorService.getProveedorById(id));
    }

    // 5. obtener todos los proveedores
    @GetMapping("/all")
    public ResponseEntity<List<ProveedorDTO>> getAllProveedor()  {
        return ResponseEntity.ok(proveedorService.getAllProveedores());
    }

}
