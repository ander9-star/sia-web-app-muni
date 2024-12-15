package pe.sia.presentation.controller.problemaController;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pe.sia.presentation.dto.problemaDTO.DetalleProblemaDTO;
import pe.sia.service.interfaces.DetalleProblemaService;

@RestController
public class FalloIncidenciaController {

    private final DetalleProblemaService detalleProblemaService;

    public FalloIncidenciaController(DetalleProblemaService detalleProblemaService) {
        this.detalleProblemaService = detalleProblemaService;
    }

    @GetMapping("/soporte-tecnico/all-problema")
    public ResponseEntity<DetalleProblemaDTO> getAll() {
        return ResponseEntity.ok(detalleProblemaService.findAllDetalleProblema());
    }

    @GetMapping("/soporte-tecnico/get-incidencia/{codigoProblema}")
    public ResponseEntity<DetalleProblemaDTO> getByIdIncidencia(@PathVariable("codigoProblema") String codigoProblema) {
        return ResponseEntity.ok(detalleProblemaService.buscarDetalleProblema(codigoProblema));
    }

    @PostMapping("/soporte-tecnico/create-dp")
    public ResponseEntity<DetalleProblemaDTO> create(@RequestBody DetalleProblemaDTO item) {
        return  ResponseEntity.ok(detalleProblemaService.insertarDetalleProblema(item));
    }

    @PutMapping("/soporte-tecnico/update-incidencia/{id}")
    public ResponseEntity<DetalleProblemaDTO> update(@PathVariable("id") Integer id, @RequestBody DetalleProblemaDTO item) {
        return  ResponseEntity.ok(detalleProblemaService.actualizarDetalleProblema(id, item));
    }

    @DeleteMapping("/soporte-tecnico/delete-incidencia/{id}")
    public ResponseEntity<DetalleProblemaDTO> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(detalleProblemaService.eliminarDetalleProblema(id));
    }

    @GetMapping("/admin/problema-solution")
    public ResponseEntity<Integer> getIncidenciasSolucionadas() {
        return ResponseEntity.ok(detalleProblemaService.getDetalleProblemaSolucionados());
    }
    
    @GetMapping("/admin/problema-resueltas-promedio")
    public ResponseEntity<Integer> getIncidenciasResueltasPromedio() {
        int promedio = detalleProblemaService.getPromedioDetalleProblemaResueltos();
        return ResponseEntity.ok(promedio); 
    }

    @GetMapping("/admin/problema-max-empleado")
    public ResponseEntity<Map<String, Object>> getEmpleadoMaxIncidencias() {
        return ResponseEntity.ok(detalleProblemaService.getMaxEmpleadoDetalleProblema());
    }

    @GetMapping("/admin/problema-max-empleado-promedio")
    public ResponseEntity<Integer> getEmpleadoMaxIncidenciasPromedio() {
        return ResponseEntity.ok(detalleProblemaService.getPromedioMaxEmpleadoDetalleProblema());
    }

    @GetMapping("/soporte-tecnico/total-incidencia-ayer")
    public ResponseEntity<Integer> getIncidenciasTotalesAyer() {
        return ResponseEntity.ok(detalleProblemaService.getTotalDetalleProblemaAyer());
    }
    
    @GetMapping("/soporte-tecnico/promedio-incidencia-ayer")
    public ResponseEntity<Integer> getPromedioIncidenciasAyerHoy() {
        return ResponseEntity.ok(detalleProblemaService.getPromedioDetalleProbemaEntreAyerHoy());
    }

    @GetMapping("/adminuser/cantidad-mes")
    public ResponseEntity<DetalleProblemaDTO> getCantidadFalloPorMes() {
        return ResponseEntity.ok(detalleProblemaService.getCantidadTotalDetallePromedioPorMes());
    }

    @GetMapping("/adminuser/total-problema")
    public ResponseEntity<DetalleProblemaDTO> getCantidadTotalIncidencias() {
        return ResponseEntity.ok(detalleProblemaService.getMedidasDetalleProblemaMantenimiento());
    }

    @GetMapping("/adminuser/total-dia-mes")
    public ResponseEntity<DetalleProblemaDTO> getTotalIncidenciasDiasMes() {
        return ResponseEntity.ok(detalleProblemaService.getCantidadDetalleProblemaPorDiaMesActualAnterior());
    }

    @GetMapping("/adminuser/total-mes")
    public ResponseEntity<DetalleProblemaDTO> getIncidenciasTotalMesActualAnterior() {
        return ResponseEntity.ok(detalleProblemaService.getDetalleProblemaTotalMesActualAnterior());
    }

    @GetMapping("/adminuser/total-prioridad-mes")
    public ResponseEntity<DetalleProblemaDTO> getTotalIncidenciasFalloByPrioridad() {
        return ResponseEntity.ok(detalleProblemaService.getTotalDetalleProblemaByPrioridadByCategoria());
    }

}
