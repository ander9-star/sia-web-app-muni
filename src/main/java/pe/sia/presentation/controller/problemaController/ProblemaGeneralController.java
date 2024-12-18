package pe.sia.presentation.controller.problemaController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.sia.presentation.dto.problemaDTO.ProblemaGeneralDTO;
import pe.sia.service.interfaces.ProblemaGeneralService;
import java.util.Map;

@RestController
public class ProblemaGeneralController {

    private  final ProblemaGeneralService problemaGeneralService;

    public ProblemaGeneralController(ProblemaGeneralService problemaGeneralService) {
        this.problemaGeneralService = problemaGeneralService;
    }

    @GetMapping("/admin/countmes-problema")
    public ResponseEntity<Integer> getIncidenciasByMonth () {
        Integer countIncidencias = problemaGeneralService.getTotalIncidenciasPorMes();
        return ResponseEntity.ok(countIncidencias);
    }

    @GetMapping("/admin/comparemes-problema")
    public ResponseEntity<Map<String, Object>> getIncidenciasComparacion() {
        Map<String, Object> resutados = problemaGeneralService.getIncidenciasDosMonthComparacion();
        return ResponseEntity.ok(resutados);
    }

    @GetMapping("/adminuser/countday-problema")
    public ResponseEntity<Integer> getIncidenciasByDay() {
        Integer countIncidencias = problemaGeneralService.getTotalIncidenciasPorDia();
        return ResponseEntity.ok(countIncidencias);
    }

    @GetMapping("/adminuser/compareday-problema")
    public ResponseEntity<Map<String, Object>> getIncidenciasDayComparacion() {
        Map<String, Object> resutados = problemaGeneralService.getAmountProGenByDayActulaAnterior();
        return ResponseEntity.ok(resutados);
    }

    @GetMapping("/adminuser/data-problema-general/{idProblemaGeneral}/{idUsuario}/{esAdmin}")
    public ResponseEntity<ProblemaGeneralDTO> getDataNormalizadaProblemaGeneral(
            @PathVariable("idProblemaGeneral") Integer idProblemaGeneral,
            @PathVariable("idUsuario") Integer idUsuario,
            @PathVariable("esAdmin") Boolean esAdmin
    ) {
        ProblemaGeneralDTO data = problemaGeneralService.getDataNormalizadaProblemaGeneral(idProblemaGeneral, idUsuario, esAdmin);
        return ResponseEntity.ok(data);
    }

    @PostMapping("/soporte-tecnico/create-problema-general")
    public ResponseEntity<ProblemaGeneralDTO> createProblemaGeneral(@RequestBody ProblemaGeneralDTO problemaGeneralDTO) {
        ProblemaGeneralDTO problemaGeneral = problemaGeneralService.createProblemaGeneral(problemaGeneralDTO);
        return ResponseEntity.ok(problemaGeneral);
    }

    @DeleteMapping("/soporte-tecnico/delete-problema-general/{idProblemaGeneral}")
    public ResponseEntity<ProblemaGeneralDTO> deleteProblemaGeneral(@PathVariable("idProblemaGeneral") Integer idProblemaGeneral) {
        ProblemaGeneralDTO problemaGeneral = problemaGeneralService.deleteProblemaGeneral(idProblemaGeneral);
        return ResponseEntity.ok(problemaGeneral);
    }

    @GetMapping("/admin/pg-total-mes")
    public ResponseEntity<Integer> getProblemaGeneralAlMesActual() {
        Integer totalProblemaGeneral = problemaGeneralService.getProblemaGeneralAlMesActual();
        return ResponseEntity.ok(totalProblemaGeneral);
    }

}
