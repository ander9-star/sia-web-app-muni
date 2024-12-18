package pe.sia.presentation.controller.activosController;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pe.sia.presentation.dto.activosDTO.ProveedorDTO;
import pe.sia.service.interfaces.ProveedorService;

/*
 * @author villata carnero jhon
 */

@RestController
public class ProveedorController {

    private final ProveedorService proveedorService;

    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    // 1. peticion para crear un proveedor
    @PostMapping("/admin/create-proveedor")
    public ResponseEntity<ProveedorDTO> createProveedor(@RequestBody ProveedorDTO proveedorDTO) {
        return ResponseEntity.ok(proveedorService.createProveedor(proveedorDTO));
    }

    // 2. peticion para actualizar un proveedor
    @PutMapping("/admin/update-proveedor/{id}")
    public ResponseEntity<ProveedorDTO> updateProveedor(@PathVariable Integer id, @RequestBody ProveedorDTO proveedorDTO) {
        return ResponseEntity.ok(proveedorService.updateProveedor(id, proveedorDTO));
    }

    // 3. peticion para eliminar un proveedor
    @DeleteMapping("/admin/delete-proveedor/{id}")
    public ResponseEntity<Void> deleteProveedor(@PathVariable Integer id) {
        proveedorService.deleteProveedor(id);
        return ResponseEntity.noContent().build();
    }

    // 4. buscar un proveedor por id
    @GetMapping("/admin/proveedor-by-id/{id}")
    public ResponseEntity<ProveedorDTO> getProveedorById(@PathVariable Integer id) {
        return ResponseEntity.ok(proveedorService.getProveedorById(id));
    }

    // 5. obtener todos los proveedores
    @GetMapping("/admin/proveedor-all")
    public ResponseEntity<List<ProveedorDTO>> getAllProveedor()  {
        return ResponseEntity.ok(proveedorService.getAllProveedores());
    }

    // 6. obtener todos los proveedores con su data completa
    @GetMapping("/adminuser/data-proveedor")
    public ResponseEntity<ProveedorDTO> getDataProveedor()  {
        return ResponseEntity.ok(proveedorService.getDataProveedor());
    }

}
