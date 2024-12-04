package pe.sia.presentation.controller.falloIncidenciaController;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pe.sia.presentation.dto.incienciasDTO.IncidenciaFalloDTO;
import pe.sia.service.interfaces.FalloIncidenciaService;

@RestController
public class FalloIncidenciaController {

    private final FalloIncidenciaService falloIncidenciaService;

    public FalloIncidenciaController(FalloIncidenciaService falloIncidenciaService) {
        this.falloIncidenciaService = falloIncidenciaService;
    }

    @GetMapping("/soporte-tecnico/all-incidencias")
    public ResponseEntity<IncidenciaFalloDTO> getAll() {
        return ResponseEntity.ok(falloIncidenciaService.getIncidenciaFallo());
    }

    @GetMapping("/soporte-tecnico/get-incidencia/{codigoProblema}")
    public ResponseEntity<IncidenciaFalloDTO> getByIdIncidencia(@PathVariable("codigoProblema") String codigoProblema) {
        return ResponseEntity.ok(falloIncidenciaService.buscarIncidencia(codigoProblema));
    }

    @PostMapping("/soporte-tecnico/create-incidencia")
    public ResponseEntity<IncidenciaFalloDTO> create(@RequestBody IncidenciaFalloDTO item) {
        return  ResponseEntity.ok(falloIncidenciaService.insertarIncidencia(item));
    }

    @PutMapping("/soporte-tecnico/update-incidencia/{id}")
    public ResponseEntity<IncidenciaFalloDTO> update(@PathVariable("id") Integer id, @RequestBody IncidenciaFalloDTO item) {
        return  ResponseEntity.ok(falloIncidenciaService.actualizarIncidencia(id, item));
    }

    @DeleteMapping("/soporte-tecnico/delete-incidencia/{id}")
    public ResponseEntity<IncidenciaFalloDTO> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(falloIncidenciaService.eliminarIncidencia(id));
    }

    @GetMapping("/admin/countmes-incidencias")
    public ResponseEntity<Integer> getIncidenciasByMonth () {
        Integer countIncidencias = falloIncidenciaService.getTotalIncidenciasPorMes(); 
        return ResponseEntity.ok(countIncidencias);
    }

    @GetMapping("/admin/comparemes-incidencias")
    public ResponseEntity<Map<String, Object>> getIncidenciasComparacion() {
        Map<String, Object> resutados = falloIncidenciaService.getIncidenciasDosMonthComparacion();
        return ResponseEntity.ok(resutados);
    }

    @GetMapping("/adminuser/countday-incidencias")
    public ResponseEntity<Integer> getIncidenciasByDay() {
        Integer countIncidencias = falloIncidenciaService.getTotalIncidenciasPorDia(); 
        return ResponseEntity.ok(countIncidencias);
    }

    @GetMapping("/adminuser/compareday-incidencias")
    public ResponseEntity<Map<String, Object>> getIncidenciasDayComparacion() {
        Map<String, Object> resutados = falloIncidenciaService.getIncidenciasDosDayComparacion();
        return ResponseEntity.ok(resutados);
    }

    @GetMapping("/admin/incidencias-solution")
    public ResponseEntity<Integer> getIncidenciasSolucionadas() {
        return ResponseEntity.ok(falloIncidenciaService.getIncidenciasTotalesSolucionadas());
    }
    
    @GetMapping("/admin/incidencias-resueltas-promedio")
    public ResponseEntity<Integer> getIncidenciasResueltasPromedio() {
        int promedio = falloIncidenciaService.getPromedioIncidenciasSolucionadas();
        return ResponseEntity.ok(promedio); 
    }

    @GetMapping("/admin/incidencias-max-empleado")
    public ResponseEntity<Map<String, Object>> getEmpleadoMaxIncidencias() {
        return ResponseEntity.ok(falloIncidenciaService.getEmpleadoCantidadMaxIncidencias());
    }

    @GetMapping("/admin/incidencias-max-empleado-promedio")
    public ResponseEntity<Integer> getEmpleadoMaxIncidenciasPromedio() {
        return ResponseEntity.ok(falloIncidenciaService.getPromedioIncidenciasEmpleado());
    }

    @GetMapping("/soporte-tecnico/total-incidencia-ayer")
    public ResponseEntity<Integer> getIncidenciasTotalesAyer() {
        return ResponseEntity.ok(falloIncidenciaService.getIncidenciasTotalesAyer());
    }
    
    @GetMapping("/soporte-tecnico/promedio-incidencia-ayer")
    public ResponseEntity<Integer> getPromedioIncidenciasAyerHoy() {
        return ResponseEntity.ok(falloIncidenciaService.getPromedioIncidenciasAyerHoy());
    }   
    
    @GetMapping("/soporte-tecnico/mantenimiento-incidencia-hoy-ayer")
    public ResponseEntity<Map<String, Object>> getTotalManenimientoHoyAyer() {
        return ResponseEntity.ok(falloIncidenciaService.getTotalManenimientoHoyAyer());
    }

    @GetMapping("/soporte-tecnico/auditoria-incidencia-hoy-total")
    public ResponseEntity<Map<String, Object>> getTotalAuditoriaHoyTotal() {
        return ResponseEntity.ok(falloIncidenciaService.getTotalAuditoriaHoyTotal());
    }

    @GetMapping("/soporte-tecnico/all-table")
    public ResponseEntity<IncidenciaFalloDTO> getTableResults() {
        return ResponseEntity.ok(falloIncidenciaService.getTableResults());
    }

    @GetMapping("/adminuser/cantidad-mes")
    public ResponseEntity<IncidenciaFalloDTO> getCantidadFalloPorMes() {
        return ResponseEntity.ok(falloIncidenciaService.getCantidadTotalIncidencaPorMes());
    }

    @GetMapping("/adminuser/total-incidencias")
    public ResponseEntity<IncidenciaFalloDTO> getCantidadTotalIncidencias() {
        return ResponseEntity.ok(falloIncidenciaService.getTotalIncidencias());
    }

    @GetMapping("/adminuser/total-dia-mes")
    public ResponseEntity<IncidenciaFalloDTO> getTotalIncidenciasDiasMes() {
        return ResponseEntity.ok(falloIncidenciaService.getTotalIncidenciasDiasMes());
    }

    @GetMapping("/adminuser/total-mes")
    public ResponseEntity<IncidenciaFalloDTO> getIncidenciasTotalMesActualAnterior() {
        return ResponseEntity.ok(falloIncidenciaService.getIncidenciasTotalMesActualAnterior());
    }

    @GetMapping("/adminuser/total-prioridad-mes")
    public ResponseEntity<IncidenciaFalloDTO> getTotalIncidenciasFalloByPrioridad() {
        return ResponseEntity.ok(falloIncidenciaService.getTotalIncidenciasFalloByPrioridad());
    }

}
