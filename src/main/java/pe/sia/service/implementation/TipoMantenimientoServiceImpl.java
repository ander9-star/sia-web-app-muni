package pe.sia.service.implementation;

import org.springframework.stereotype.Service;
import pe.sia.persistence.entity.problema.TipoMantenimiento;
import pe.sia.persistence.repository.problemaRepository.TipoMantenimientoRepository;
import pe.sia.presentation.dto.problemaDTO.TipoMantenimientoDTO;
import pe.sia.service.interfaces.TipoMantenimientoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TipoMantenimientoServiceImpl implements TipoMantenimientoService {

    private final TipoMantenimientoRepository tipoMantenimientoRepository;

    public TipoMantenimientoServiceImpl(TipoMantenimientoRepository tipoMantenimientoRepository) {
        this.tipoMantenimientoRepository = tipoMantenimientoRepository;
    }

    @Override
    public TipoMantenimientoDTO insertTipoMantenimiento(TipoMantenimiento tipoMantenimiento) {
        TipoMantenimientoDTO requestDTO = new TipoMantenimientoDTO();
        try {
            TipoMantenimiento tipoMantenimientoNew = new TipoMantenimiento();
            tipoMantenimientoNew.setNombre(tipoMantenimiento.getNombre());
            tipoMantenimientoNew.setDescripcion(tipoMantenimiento.getDescripcion());
            TipoMantenimiento newTipoMantenimiento = tipoMantenimientoRepository.save(tipoMantenimientoNew);
            if (newTipoMantenimiento.getId() > 0) {
                requestDTO.setStatusCode(201);
                requestDTO.setMessage("Tipo de mantenimiento creado con éxito");
                requestDTO.setTipoMantenimiento(newTipoMantenimiento);
            }
        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setError("Ha ocurrido un error inesperado al crear el tipo de mantenimiento: " + e.getMessage());
        }
        return requestDTO;
    }

    @Override
    public TipoMantenimientoDTO updateTipoMantenimiento(Integer idTipoMantenimiento, TipoMantenimiento tipoMantenimiento) {
        TipoMantenimientoDTO requestDTO = new TipoMantenimientoDTO();
        try {
            Optional<TipoMantenimiento> optionalTipoMantenimiento = tipoMantenimientoRepository.findById(idTipoMantenimiento);
            optionalTipoMantenimiento.ifPresentOrElse(tipoMantenimientoUpdate -> {
                tipoMantenimientoUpdate.setNombre(tipoMantenimiento.getNombre());
                tipoMantenimientoUpdate.setDescripcion(tipoMantenimiento.getDescripcion());
                tipoMantenimientoRepository.save(tipoMantenimientoUpdate);
                requestDTO.setStatusCode(200);
                requestDTO.setMessage("Tipo de mantenimiento actualizado con éxito");
                requestDTO.setTipoMantenimiento(tipoMantenimientoUpdate);
            }, () -> {
                requestDTO.setStatusCode(404);
                requestDTO.setError("Tipo de mantenimiento no encontrado");
            });

        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setError("Ha ocurrido un error inesperado al actualizar el tipo de mantenimiento: " + e.getMessage());
        }
        return requestDTO;
    }

    @Override
    public TipoMantenimientoDTO deleteTipoMantenimiento(Integer idTipoMantenimiento) {
        TipoMantenimientoDTO requestDTO = new TipoMantenimientoDTO();
        try {
            Optional<TipoMantenimiento> tipoMantenimientoDelete = tipoMantenimientoRepository.findById(idTipoMantenimiento);
            tipoMantenimientoDelete.ifPresentOrElse(tipoMantenimiento -> {
                tipoMantenimientoRepository.delete(tipoMantenimiento);
                requestDTO.setStatusCode(204);
                requestDTO.setMessage("Tipo de mantenimiento eliminado con éxito");
            }, () -> {
                requestDTO.setStatusCode(404);
                requestDTO.setError("Tipo de mantenimiento no encontrado");
            });
        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setError("Ha ocurrido un error inesperado al eliminar el tipo de mantenimiento: " + e.getMessage());
        }
        return requestDTO;
    }

    @Override
    public TipoMantenimientoDTO listAllTipoMantenimiento() {
        TipoMantenimientoDTO requestDTO = new TipoMantenimientoDTO();
        try {
            List<TipoMantenimiento> tipoMantenimientoList = tipoMantenimientoRepository.findAll();
            List<TipoMantenimientoDTO> tipoMantenimientoDTOList = new ArrayList<>();
            if (!tipoMantenimientoList.isEmpty()) {
                for (TipoMantenimiento tipoMantenimiento : tipoMantenimientoList) {
                    TipoMantenimientoDTO tipoMantenimientoDTO = mapToDTO(tipoMantenimiento);
                    tipoMantenimientoDTOList.add(tipoMantenimientoDTO);
                }
                requestDTO.setStatusCode(200);
                requestDTO.setMessage("Se ha obtenido con éxito la lista de tipo de mantenimiento");
                requestDTO.setTipoMantenimientoDTOList(tipoMantenimientoDTOList);
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

    private TipoMantenimientoDTO mapToDTO(TipoMantenimiento tipoMantenimiento) {
        TipoMantenimientoDTO tipoMantenimientoDTO = new TipoMantenimientoDTO();
        tipoMantenimientoDTO.setId(tipoMantenimiento.getId());
        tipoMantenimientoDTO.setNombre(tipoMantenimiento.getNombre());
        tipoMantenimientoDTO.setDescripcion(tipoMantenimiento.getDescripcion());
        return tipoMantenimientoDTO;
    }

    @Override
    public TipoMantenimientoDTO getDataShortTipoMantenimiento() {
        TipoMantenimientoDTO requestDTO = new TipoMantenimientoDTO();
        try {
            List<Object[]> resultTable = tipoMantenimientoRepository.getShortDataTipoMantenimiento();
            List<TipoMantenimientoDTO> tipoMantenimientoDTOList = new ArrayList<>();
            if (!resultTable.isEmpty()) {
                for (Object[] row : resultTable) {
                    TipoMantenimientoDTO tipoMantenimientoDTO = new TipoMantenimientoDTO();
                    tipoMantenimientoDTO.setStatusCode(200);
                    tipoMantenimientoDTO.setId((Integer) row[0]);
                    tipoMantenimientoDTO.setNombre((String) row[1]);
                    tipoMantenimientoDTOList.add(tipoMantenimientoDTO);
                }
                requestDTO.setStatusCode(200);
                requestDTO.setMessage("Se ha obtenido con éxito la data short de tipo mantenimiento");
                requestDTO.setTipoMantenimientoDTOList(tipoMantenimientoDTOList);
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
