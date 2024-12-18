package pe.sia.service.implementation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.sia.persistence.entity.problema.ProblemaGeneral;
import pe.sia.persistence.repository.activosRepository.ActivoMaestroRepository;
import pe.sia.persistence.repository.actoresRepository.UsuarioRepository;
import pe.sia.persistence.repository.problemaRepository.ProblemaGeneralRepository;
import pe.sia.presentation.dto.problemaDTO.ProblemaGeneralDTO;
import pe.sia.service.interfaces.ProblemaGeneralService;
import pe.sia.util.UtilsApp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class ProblemaGeneralServiceImpl implements ProblemaGeneralService {

    private final ProblemaGeneralRepository problemaGeneralRepository;
    private final ActivoMaestroRepository activoMaestroRepository;
    private final UsuarioRepository usuarioRepository;

    public ProblemaGeneralServiceImpl(ProblemaGeneralRepository problemaGeneralRepository, ActivoMaestroRepository activoMaestroRepository,
                                      UsuarioRepository usuarioRepository) {
        this.problemaGeneralRepository = problemaGeneralRepository;
        this.activoMaestroRepository = activoMaestroRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Integer getProblemaGeneralAlMesActual() {
        return problemaGeneralRepository.getProblemaGeneralAlMesActual();
    }

    @Override
    public Integer getTotalIncidenciasPorMes() {
        return problemaGeneralRepository.getProblemaGeneralPorMes();
    }

    @Override
    public Integer getTotalIncidenciasPorDia() {
        return problemaGeneralRepository.countIncidenciasByDay();
    }

    @Override
    public Map<String, Object> getIncidenciasDosMonthComparacion() {
        List<Map<String, Object>> resultadosTable = problemaGeneralRepository.getIncidenciasComparacion();

        if (resultadosTable.size() < 2) {
            // si no estan los dos meses
            return Map.of("mesActual", 0, "mesAnterior", 0);
        }

        Map<String, Object> mesActual = resultadosTable.get(0);
        Map<String, Object> mesAnterior = resultadosTable.get(1);

        int totalMesActual = (int) mesActual.get("total");
        int totalMesAnterior = (int) mesAnterior.get("total");
        double porcentaje = ((double)(totalMesActual - totalMesAnterior) / totalMesAnterior) * 100;
        boolean incremento = porcentaje >= 0;

        return Map.of(
                "porcentaje", Math.round(porcentaje),
                "esIncremento", incremento
        );
    }

    @Override
    public Map<String, Object> getAmountProGenByDayActulaAnterior() {
        List<Map<String, Object>> resultadoTable = problemaGeneralRepository.getIncidenciasDiaComparacion();

        if (resultadoTable.size() < 2) {
            return Map.of("diaActual", 0, "diaAnterior", 0);
        }

        Map<String, Object> diaActual = resultadoTable.get(0);
        Map<String, Object> diaAnterior = resultadoTable.get(1);

        int totalDiaActual = (Integer) diaActual.get("total");
        int totalDiaAnterior = (Integer) diaAnterior.get("total");

        double porcentajeActual = getPorcentaje(totalDiaAnterior, totalDiaActual);
        double porcentajeAnterior = getPorcentaje(totalDiaActual, totalDiaAnterior);

        boolean incremento = porcentajeActual >= 0;

        return Map.of(
                "porcentajeDiaActual", Math.round(porcentajeActual),
                "porcentajeDiaAnterior", Math.round(porcentajeAnterior),
                "esIncremento", incremento,
                "diaAnterior", totalDiaAnterior
        );
    }

    @Override
    public ProblemaGeneralDTO getDataNormalizadaProblemaGeneral(Integer idProblemaGeneral, Integer idUsuario, Boolean esAdmin) {
        ProblemaGeneralDTO requestDTO = new ProblemaGeneralDTO();
        try {
            List<Object[]> resultTable = problemaGeneralRepository.getDataNormalizadaProblemaGeneral(idProblemaGeneral, idUsuario, esAdmin);
            List<ProblemaGeneralDTO> listProblemaGeneral = new ArrayList<>();
            if (!resultTable.isEmpty()) {
                for (Object[] row : resultTable) {
                    ProblemaGeneralDTO problemaGeneralDTO = new ProblemaGeneralDTO();
                    problemaGeneralDTO.setStatusCode(200);
                    problemaGeneralDTO.setIdProblemaGeneral((Integer) row[0]);
                    problemaGeneralDTO.setCodigoProblemaGeneral(row[1].toString());
                    problemaGeneralDTO.setCodigoBien(row[2].toString());
                    problemaGeneralDTO.setNombreActivo(row[3].toString());
                    problemaGeneralDTO.setTipoActivo(row[4].toString());
                    problemaGeneralDTO.setNombreEmpleado(row[5].toString());
                    problemaGeneralDTO.setNombreUsuario(row[6].toString());
                    problemaGeneralDTO.setFechaOcurrencia(UtilsApp.formatearFecha((Instant) row[7]));
                    problemaGeneralDTO.setCantidadDetalleProblema((Integer) row[8]);
                    listProblemaGeneral.add(problemaGeneralDTO);
                }
                requestDTO.setStatusCode(200);
                requestDTO.setMessage("Se ha obtenido la data normalizada de problema general");
                requestDTO.setProblemaGeneralDTOList(listProblemaGeneral);
            } else {
                requestDTO.setStatusCode(404);
                requestDTO.setMessage("Lista vacia, no hay datos");
            }
            return requestDTO;
        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setError("Ha ocurrido un error inexperado al obtener la lista de problema general: " + e.getMessage());
            return requestDTO;
        }
    }

    @Override
    public ProblemaGeneralDTO createProblemaGeneral(ProblemaGeneralDTO problemaGeneralDTO) {
        ProblemaGeneralDTO requestDTO = new ProblemaGeneralDTO();
        try {
            ProblemaGeneral problemaGeneral = new ProblemaGeneral();
            problemaGeneral.setActivoMaestro(activoMaestroRepository.findById(problemaGeneralDTO.getIdActivoMaestro()).orElse(null));
            problemaGeneral.setUsuario(usuarioRepository.findById(problemaGeneralDTO.getIdUsuario()).orElse(null));
            problemaGeneral.setFechaOcurrencia(UtilsApp.formatearFechaInstant(problemaGeneralDTO.getFechaOcurrencia()));
            ProblemaGeneral newProblemaGeneral = problemaGeneralRepository.save(problemaGeneral);
            if (newProblemaGeneral.getId() > 0) {
                requestDTO.setStatusCode(201);
                requestDTO.setMessage("Problema general creado con éxito");
                requestDTO.setProblemaGeneral(newProblemaGeneral);
            }
            return requestDTO;
        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setMessage("Error al crear el problema general " + e.getMessage());
            return requestDTO;
        }
    }

    @Override
    public ProblemaGeneralDTO deleteProblemaGeneral(Integer idProblemaGeneral) {
        ProblemaGeneralDTO requestDTO = new ProblemaGeneralDTO();
        try {
            Optional<ProblemaGeneral> optionalProblemaGeneral = problemaGeneralRepository.findById(idProblemaGeneral);
            optionalProblemaGeneral.ifPresentOrElse(problemaGeneral -> {
                problemaGeneralRepository.delete(problemaGeneral);
                requestDTO.setStatusCode(200);
                requestDTO.setMessage("Problema general eliminado con éxito");
            }, () -> {
                requestDTO.setStatusCode(404);
                requestDTO.setMessage("Problema general no encontrado");
            });
        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setMessage("Error al eliminar el problema general " + e.getMessage());
        }
        return requestDTO;
    }

    private double getPorcentaje(int totalDiaAnterior, int totalDiaActual) {
        double porcentaje;
        if (totalDiaAnterior == 0) {
            // Si el día anterior tiene 0 problema
            if (totalDiaActual > 0) {
                porcentaje = 100.0; // Se considera un incremento del 100%
            } else {
                porcentaje = 0.0; // No hay cambios si ambos son 0
            }
        } else {
            // Si el día anterior tiene problema, calculamos el porcentaje normalmente
            int diferencia = totalDiaActual - totalDiaAnterior;
            // Se asegura que la división se realice en el contexto de double
            double valorRelativo = (double) diferencia / totalDiaAnterior; // Esto ahora será un double
            porcentaje = valorRelativo * 100; // Calcula el porcentaje
        }
        return porcentaje;
    }

}
