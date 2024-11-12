package pe.sia.presentation.controller.falloIncidenciaController;

import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pe.sia.persistence.entity.incidencias.FalloIncidencia;
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

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable("id") FalloIncidencia id) {
        /*Optional<?> existingItemOptional = repository.findById(id);

        if (existingItemOptional.isPresent()) {
            return new ResponseEntity<>(existingItemOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }*/
        return null;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody FalloIncidencia item) {
        /*try {
            entityClassName savedItem = repository.save(item);
            return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }*/
        return null;
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody FalloIncidencia item) {
        /*Optional<?> existingItemOptional = falloIncidenciaService.findById(id);
        if (existingItemOptional.isPresent()) {
            //entityClassName existingItem = existingItemOptional.get();
            System.out.println("TODO for developer - update logic is unique to entity and must be implemented manually.");
            //existingItem.setSomeField(item.getSomeField());
            //return new ResponseEntity<>(repository.save(existingItem), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }*/
        return null;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Integer id) {
        try {
            //repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
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
    
}
