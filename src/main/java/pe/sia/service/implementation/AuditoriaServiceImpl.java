package pe.sia.service.implementation;

import org.springframework.stereotype.Service;
import pe.sia.persistence.entity.problema.Auditoria;
import pe.sia.persistence.repository.problemaRepository.AuditoriaRepository;
import pe.sia.persistence.repository.problemaRepository.MantenimientoRepository;
import pe.sia.presentation.dto.problemaDTO.AuditoriaDTO;
import pe.sia.service.interfaces.AuditoriaService;
import pe.sia.util.UtilsApp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AuditoriaServiceImpl implements AuditoriaService {

    private final AuditoriaRepository auditoriaRepository;
    private final MantenimientoRepository mantenimientoRepository;

    public AuditoriaServiceImpl(AuditoriaRepository auditoriaRepository, MantenimientoRepository mantenimientoRepository) {
        this.auditoriaRepository = auditoriaRepository;
        this.mantenimientoRepository = mantenimientoRepository;
    }

    @Override
    public AuditoriaDTO insertAuditoria(AuditoriaDTO auditoriaDTO) {
        AuditoriaDTO requestDTO = new AuditoriaDTO();
        try {
            Auditoria auditoria = new Auditoria();
            auditoria.setFechaCambio(UtilsApp.formatearFechaInstant(auditoriaDTO.getFechaCambio()));
            auditoria.setCambioRealizado(auditoriaDTO.getCambioRealizado());
            auditoria.setMantenimiento(mantenimientoRepository.findById(auditoriaDTO.getIdMantenimiento()).orElseThrow());
            Auditoria newAuditoria = auditoriaRepository.save(auditoria);
            if(newAuditoria.getId() > 0) {
                requestDTO.setStatusCode(201);
                requestDTO.setMessage("Auditoria creada con exito");
                requestDTO.setAuditoria(auditoria);
            }
        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setError("Ha ocurrido un error inesperado al crear la auditoria: " + e.getMessage());
        }
        return requestDTO;
    }

    @Override
    public AuditoriaDTO updateAuditoria(Integer idAuditoria, AuditoriaDTO auditoriaDTO) {
        AuditoriaDTO requestDTO = new AuditoriaDTO();
        try {
            Optional<Auditoria> auditoriaOld = auditoriaRepository.findById(idAuditoria);
            if(auditoriaOld.isPresent()) {
                Auditoria auditoriaGet = auditoriaOld.get();
                auditoriaGet.setFechaCambio(UtilsApp.formatearFechaInstant(auditoriaDTO.getFechaCambio()));
                auditoriaGet.setCambioRealizado(auditoriaDTO.getCambioRealizado());
                auditoriaGet.setMantenimiento(mantenimientoRepository.findById(auditoriaDTO.getIdMantenimiento()).orElseThrow(() -> new RuntimeException("Error")));
                Auditoria auditoriaUpdate = auditoriaRepository.save(auditoriaGet);
                requestDTO.setStatusCode(201);
                requestDTO.setMessage("Auditoria actualizada con exito");
                requestDTO.setAuditoria(auditoriaUpdate);
            }
        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setError("Ha ocurrido un error inesperado al actualizar la auditoria: " + e.getMessage());
        }
        return requestDTO;
    }

    @Override
    public AuditoriaDTO deleteAuditoria(Integer idAuditoria) {
        AuditoriaDTO requestDTO = new AuditoriaDTO();
        try {
            Optional<Auditoria> auditoriaOld = auditoriaRepository.findById(idAuditoria);
            if(auditoriaOld.isPresent()) {
                auditoriaRepository.deleteById(idAuditoria);
                requestDTO.setStatusCode(204);
                requestDTO.setMessage("Auditoria eliminada con exito");
            } else {
                requestDTO.setStatusCode(404);
                requestDTO.setMessage("La auditoria no ha sido encontrado");
            }
        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setError("Ha ocurrido un error inesperado al eliminar la auditoria: " + e.getMessage());
        }
        return requestDTO;
    }

    @Override
    public AuditoriaDTO getAuditoriaMantenimiento(Integer idMantenimiento) {
        AuditoriaDTO requestDTO = new AuditoriaDTO();
        try {
            List<Object[]> resultTable = auditoriaRepository.getAuditoriaMantenimiento(idMantenimiento);
            List<AuditoriaDTO> listAuditoria = new ArrayList<>();
            if(!resultTable.isEmpty()) {
                for (Object[] row : resultTable) {
                    AuditoriaDTO auditoriaDTO = new AuditoriaDTO();
                    auditoriaDTO.setStatusCode(200);
                    auditoriaDTO.setIdMantenimiento((Integer) row[0]);
                    auditoriaDTO.setFechaCambio(UtilsApp.formatearFecha((Instant) row[1]));
                    auditoriaDTO.setCambioRealizado((String) row[2]);
                    listAuditoria.add(auditoriaDTO);
                }
                requestDTO.setStatusCode(200);
                requestDTO.setMessage("Se ha obtenido la data de auditoria con el mantenimiento");
                requestDTO.setAuditoriaDTOList(listAuditoria);
            }
            else {
                requestDTO.setStatusCode(404);
                requestDTO.setMessage("Lista vacia, no hay datos");
            }
            return requestDTO;
        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setError("Ha ocurrido un error inesperado al obtener la data: " + e.getMessage());
            return requestDTO;
        }
    }

    @Override
    public AuditoriaDTO getDataFullAuditoria() {
        AuditoriaDTO requestDTO = new AuditoriaDTO();
        try {
            List<Object[]> resultTable = auditoriaRepository.getDataFullAuditoria();
            List<AuditoriaDTO> listAuditoria = new ArrayList<>();
            if(!resultTable.isEmpty()) {
                for (Object[] row : resultTable) {
                    AuditoriaDTO auditoriaDTO = new AuditoriaDTO();
                    auditoriaDTO.setStatusCode(200);
                    auditoriaDTO.setIdAuditoria((Integer) row[0]);
                    auditoriaDTO.setIdMantenimiento((Integer) row[1]);
                    auditoriaDTO.setCodigoProblema((String) row[2]);
                    auditoriaDTO.setTipoMantenimiento((String) row[3]);
                    auditoriaDTO.setFechaRealizada(UtilsApp.formatearFecha((Instant) row[4]));
                    auditoriaDTO.setPersonal((String) row[5]);
                    auditoriaDTO.setFechaCambio(UtilsApp.formatearFecha((Instant) row[6]));
                    auditoriaDTO.setCambioRealizado((String) row[7]);
                    listAuditoria.add(auditoriaDTO);
                }
                requestDTO.setStatusCode(200);
                requestDTO.setMessage("Se ha obtenido la data completa de auditoria");
                requestDTO.setAuditoriaDTOList(listAuditoria);
            }
            else {
                requestDTO.setStatusCode(404);
                requestDTO.setMessage("Lista vacia, no hay datos");
            }
            return requestDTO;
        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setError("Ha ocurrido un error inesperado al obtener la data de auditoria: " + e.getMessage());
            return requestDTO;
        }
    }

    @Override
    public Map<String, Object> getTotalAuditoriaHoyTotal() {
        List<Map<String, Object>> resultadoTable = auditoriaRepository.getTotalAuditoriaHoyTotal();
        Map<String, Object> map = resultadoTable.getFirst();
        return Map.of(
                "totalHoy", map.get("totalHoy"),
                "total", map.get("total")
        );
    }
}
