package pe.sia.service.interfaces;

import pe.sia.presentation.dto.problemaDTO.ProblemaGeneralDTO;
import java.util.Map;

public interface ProblemaGeneralService {

    Integer getProblemaGeneralAlMesActual();

    Integer getTotalIncidenciasPorMes();

    Map<String, Object> getIncidenciasDosMonthComparacion();

    Integer getTotalIncidenciasPorDia();

    Map<String, Object> getAmountProGenByDayActulaAnterior();

    ProblemaGeneralDTO getDataNormalizadaProblemaGeneral(Integer idProblemaGeneral, Integer idUsuario, Boolean esAdmin);

    ProblemaGeneralDTO createProblemaGeneral(ProblemaGeneralDTO problemaGeneralDTO);

    ProblemaGeneralDTO deleteProblemaGeneral(Integer idProblemaGeneral);
}
