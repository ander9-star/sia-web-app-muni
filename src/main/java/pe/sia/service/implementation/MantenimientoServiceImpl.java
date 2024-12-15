package pe.sia.service.implementation;

import org.springframework.stereotype.Service;
import pe.sia.persistence.entity.problema.Mantenimiento;
import pe.sia.persistence.repository.actoresRepository.PersonalRepository;
import pe.sia.persistence.repository.problemaRepository.MantenimientoRepository;
import pe.sia.persistence.repository.problemaRepository.DetalleProblemaRepository;
import pe.sia.persistence.repository.problemaRepository.TipoMantenimientoRepository;
import pe.sia.presentation.dto.problemaDTO.MantenimientoDTO;
import pe.sia.service.interfaces.MantenimientoService;
import pe.sia.util.UtilsApp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MantenimientoServiceImpl implements MantenimientoService {

    private final MantenimientoRepository mantenimientoRepository;
    private final DetalleProblemaRepository detalleProblemaRepository;
    private final TipoMantenimientoRepository tipoMantenimientoRepository;
    private final PersonalRepository personalRepository;

    public MantenimientoServiceImpl(MantenimientoRepository mantenimientoRepository, DetalleProblemaRepository detalleProblemaRepository,
                                    TipoMantenimientoRepository tipoMantenimientoRepository, PersonalRepository personalRepository) {
        this.mantenimientoRepository = mantenimientoRepository;
        this.detalleProblemaRepository = detalleProblemaRepository;
        this.tipoMantenimientoRepository = tipoMantenimientoRepository;
        this.personalRepository = personalRepository;
    }

    @Override
    public MantenimientoDTO insertMantenimiento(MantenimientoDTO mantenimientoDTO) {
        MantenimientoDTO requestDTO = new MantenimientoDTO();
        try {
            Mantenimiento mantenimiento = new Mantenimiento();
            mantenimiento.setDetalleProblema(detalleProblemaRepository.findById(mantenimientoDTO.getIdDetalleProblema()).orElseThrow());
            mantenimiento.setFechaProgramada(UtilsApp.formatearFechaInstant(mantenimientoDTO.getFechaProgramada()));
            mantenimiento.setFechaRealizada(UtilsApp.formatearFechaInstant(mantenimientoDTO.getFechaRealizada()));
            mantenimiento.setTipoMantenimiento(tipoMantenimientoRepository.findById(mantenimientoDTO.getTipoMantenimientoId()).orElseThrow());
            mantenimiento.setNotas(mantenimientoDTO.getNotas());
            mantenimiento.setPersonal(personalRepository.findById(mantenimientoDTO.getPersonalId()).orElseThrow());
            Mantenimiento newMantenimiento = mantenimientoRepository.save(mantenimiento);
            if (newMantenimiento.getId() > 0) {
                requestDTO.setMantenimiento((newMantenimiento));
                requestDTO.setStatusCode(201);
                requestDTO.setMessage("Mantenimiento creado con éxito");
            }

        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setError("Ha ocurrido un error inesperado al crear el mantenimiento: " + e.getMessage());
        }
        return requestDTO;
    }

    @Override
    public MantenimientoDTO updateMantenimiento(Integer idMantenimiento, MantenimientoDTO mantenimientoBody) {
        MantenimientoDTO mantenimientoDTO = new MantenimientoDTO();
        try {
            Optional<Mantenimiento> mantenimientoOptional = mantenimientoRepository.findById(idMantenimiento);
            if (mantenimientoOptional.isPresent()) {
                Mantenimiento mantenimientoUpdate = mantenimientoOptional.get();
                mantenimientoUpdate.setDetalleProblema(detalleProblemaRepository.findById(mantenimientoBody.getIdDetalleProblema()).orElseThrow());
                mantenimientoUpdate.setFechaProgramada(UtilsApp.formatearFechaInstant(mantenimientoBody.getFechaProgramada()));
                mantenimientoUpdate.setFechaRealizada(UtilsApp.formatearFechaInstant(mantenimientoBody.getFechaRealizada()));
                mantenimientoUpdate.setTipoMantenimiento(tipoMantenimientoRepository.findById(mantenimientoBody.getTipoMantenimientoId()).orElseThrow());
                mantenimientoUpdate.setNotas(mantenimientoBody.getNotas());
                mantenimientoUpdate.setPersonal(personalRepository.findById(mantenimientoBody.getPersonalId()).orElseThrow());
                Mantenimiento newMantenimiento = mantenimientoRepository.save(mantenimientoUpdate);
                mantenimientoDTO.setMantenimiento((newMantenimiento));
                mantenimientoDTO.setStatusCode(200);
                mantenimientoDTO.setMessage("Mantenimiento actualizado con éxito");
            }
        } catch (Exception e) {
            mantenimientoDTO.setStatusCode(500);
            mantenimientoDTO.setError("Ha ocurrido un error inesperado al actualizar el mantenimiento: " + e.getMessage());
        }
        return mantenimientoDTO;
    }

    @Override
    public MantenimientoDTO deleteMantenimiento(Integer idMantenimiento) {
        MantenimientoDTO requestDTO = new MantenimientoDTO();
        try {
            Optional<Mantenimiento> mantenimientoOptional = mantenimientoRepository.findById(idMantenimiento);
            if (mantenimientoOptional.isPresent()) {
                mantenimientoRepository.deleteById(idMantenimiento);
                requestDTO.setStatusCode(204);
                requestDTO.setMessage("Mantenimiento eliminado con éxito");
            } else {
                requestDTO.setStatusCode(404);
                requestDTO.setMessage("El Mantenimiento no ha sido encontrado");
            }
        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setError("Ha ocurrido un error inesperado al eliminar el mantenimiento: " + e.getMessage());
        }
        return requestDTO;
    }

    @Override
    public MantenimientoDTO getMantenimientoFallo(Integer idFallo) {
        MantenimientoDTO requestDTO = new MantenimientoDTO();
        try {
            List<Object[]> result = mantenimientoRepository.getMantenimientoFallo(idFallo);

            if(!result.isEmpty()) {
                Object[] objeto = result.getFirst();
                requestDTO.setStatusCode(200);
                requestDTO.setMessage("La incidencia esta en mantenimiento");
                requestDTO.setIdMantenimiento((Integer) objeto[0]);
                requestDTO.setCodigoProblema((String) objeto[1]);
                requestDTO.setFechaProgramada(UtilsApp.formatearFecha((Instant) objeto[2]));
                requestDTO.setTipoMantenimiento((String) objeto[3]);
                requestDTO.setNotas((String) objeto[4]);
                requestDTO.setPersonal((String) objeto[5]);
                requestDTO.setCantidadAuditoria((Integer) objeto[6]);
            }
            else {
                requestDTO.setStatusCode(403);
                requestDTO.setMessage("La incidencia no esta en mantenimiento");
            }
            return requestDTO;

        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setError("Error al obtener la data: " + e.getMessage());
            return requestDTO;
        }
    }

    @Override
    public MantenimientoDTO getDataMantenimiento() {
        MantenimientoDTO requestDTO = new MantenimientoDTO();
        try {
            List<Object[]> resultTable = mantenimientoRepository.getDataMantenimiento();
            List<MantenimientoDTO> mantenimientoDTOList = new ArrayList<>();
            if (!resultTable.isEmpty()) {
                for (Object[] row : resultTable) {
                    MantenimientoDTO mantenimientoDTO = new MantenimientoDTO();
                    mantenimientoDTO.setStatusCode(200);
                    mantenimientoDTO.setIdMantenimiento((Integer) row[0]);
                    mantenimientoDTO.setIdDetalleProblema((Integer) row[1]);
                    mantenimientoDTO.setCodigoProblema((String) row[2]);
                    mantenimientoDTO.setNombreActivo((String) row[3]);
                    mantenimientoDTO.setDescripcion((String) row[4]);
                    mantenimientoDTO.setFechaRegistro(UtilsApp.formatearFecha((Instant) row[5]));
                    mantenimientoDTO.setFechaProgramada(UtilsApp.formatearFecha((Instant) row[6]));
                    mantenimientoDTO.setFechaRealizada(UtilsApp.formatearFecha((Instant) row[7]));
                    mantenimientoDTO.setTipoMantenimiento((String) row[8]);
                    mantenimientoDTO.setTipoMantenimientoId((Integer) row[9]);
                    mantenimientoDTO.setNotas((String) row[10]);
                    mantenimientoDTO.setPersonal((String) row[11]);
                    mantenimientoDTO.setPersonalId((Integer) row[12]);
                    mantenimientoDTO.setCantidadAuditoria((Integer) row[13]);
                    mantenimientoDTOList.add(mantenimientoDTO);
                }
                requestDTO.setStatusCode(200);
                requestDTO.setMessage("Se ha obtenido con éxito la data de mantenimiento");
                requestDTO.setListaMantenimiento(mantenimientoDTOList);
            } else {
                requestDTO.setStatusCode(404);
                requestDTO.setMessage("No hay data");
            }
            return requestDTO;

        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setError("Ha sucedido un error: " + e.getMessage());
            return requestDTO;
        }
    }

    @Override
    public Map<String, Object> getTotalManenimientoHoyAyer() {
        List<Map<String, Object>> resultadoTable = mantenimientoRepository.getTotalManenimientoHoyAyer();
        Map<String, Object> map = resultadoTable.getFirst();
        return Map.of(
                "totalHoy", map.get("totalHoy"),
                "totalAyer", map.get("totalAyer")
        );
    }

    @Override
    public MantenimientoDTO getFISinMantenimiento() {
        MantenimientoDTO requestDTO = new MantenimientoDTO();
        try {
            List<Object[]> resultTable = mantenimientoRepository.getFISinMantenimiento();
            List<MantenimientoDTO> mantenimientoDTOList = new ArrayList<>();
            if (!resultTable.isEmpty()) {
                for (Object[] row : resultTable) {
                    MantenimientoDTO mantenimientoDTO = new MantenimientoDTO();
                    mantenimientoDTO.setStatusCode(200);
                    mantenimientoDTO.setIdDetalleProblema((Integer) row[0]);
                    mantenimientoDTO.setCodigoProblema((String) row[1]);
                    mantenimientoDTOList.add(mantenimientoDTO);
                }
                requestDTO.setStatusCode(200);
                requestDTO.setMessage("Se ha obtenido con éxito los FI sin mantenimiento");
                requestDTO.setListaMantenimiento(mantenimientoDTOList);
            } else {
                requestDTO.setStatusCode(404);
                requestDTO.setMessage("No hay data");
            }
            return requestDTO;

        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setError("Ha sucedido un error: " + e.getMessage());
            return requestDTO;
        }
    }
}
