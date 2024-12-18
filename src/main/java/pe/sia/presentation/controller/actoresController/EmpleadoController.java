package pe.sia.presentation.controller.actoresController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.sia.presentation.dto.actoresDTO.EmpleadoDTO;
import pe.sia.service.interfaces.EmpleadoService;

@RestController
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping("/adminuser/top-5-empleados")
    public ResponseEntity<EmpleadoDTO> getTop5EmpleadosFallos() {
        return ResponseEntity.ok(empleadoService.getTop5Empleados());
    }

    @GetMapping("/soporte-tecnico/all-empleados-cantidad")
    public ResponseEntity<EmpleadoDTO> getTotalEmpleadosIncidencias() {
        return ResponseEntity.ok(empleadoService.getTotalEmpleadosIncidencias());
    }

    @GetMapping("/adminuser/empleado-data")
    public ResponseEntity<EmpleadoDTO> getDataEmpleados() {
        return ResponseEntity.ok(empleadoService.getDataTotalEmpleados());
    }
}
