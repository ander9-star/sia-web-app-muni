package pe.sia.service.interfaces;

import pe.sia.presentation.dto.problemaDTO.ProblemaGeneralDTO;

import java.util.Map;

public interface ProblemaGeneralService {

    Integer getProblemaGeneralAlMesActual();

    Integer getTotalIncidenciasPorMes();

    Map<String, Object> getIncidenciasDosMonthComparacion();

    Integer getTotalIncidenciasPorDia();

    Map<String, Object> getIncidenciasDosDayComparacion();

    ProblemaGeneralDTO getDataNormalizadaProblemaGeneral();

    ProblemaGeneralDTO createProblemaGeneral(ProblemaGeneralDTO problemaGeneralDTO);

    ProblemaGeneralDTO deleteProblemaGeneral(Integer idProblemaGeneral);
}
