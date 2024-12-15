package pe.sia.service.implementation;

import org.springframework.stereotype.Service;
import pe.sia.persistence.entity.problema.Prioridad;
import pe.sia.persistence.repository.problemaRepository.PrioridadRepository;
import pe.sia.presentation.dto.problemaDTO.PrioridadDTO;
import pe.sia.service.interfaces.PrioridadService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PrioridadServiceImpl implements PrioridadService  {

    private final PrioridadRepository prioridadRepository;

    public PrioridadServiceImpl(PrioridadRepository prioridadRepository) {
        this.prioridadRepository = prioridadRepository;
    }

    @Override
    public PrioridadDTO insertPrioridad(Prioridad prioridad) {
        PrioridadDTO requestDTO = new PrioridadDTO();
        try {
            Prioridad prioridadNew = new Prioridad();
            prioridadNew.setNombre(prioridad.getNombre());
            Prioridad newPrioridad = prioridadRepository.save(prioridadNew);
            if (newPrioridad.getId() > 0) {
                requestDTO.setStatusCode(201);
                requestDTO.setMessage("Prioridad creada con éxito");
                requestDTO.setPrioridad(newPrioridad);
            }
        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setError("Ha ocurrido un error inesperado al crear la prioridad: " + e.getMessage());
        }
        return requestDTO;
    }

    @Override
    public PrioridadDTO updatePrioridad(Integer idPrioridad, Prioridad prioridad) {
        PrioridadDTO requestDTO = new PrioridadDTO();
        try {
            Optional<Prioridad> prioridadUpdate = prioridadRepository.findById(idPrioridad);
            prioridadUpdate.ifPresentOrElse(prioridadUpdate1 -> {
                prioridadUpdate1.setNombre(prioridad.getNombre());
                prioridadRepository.save(prioridadUpdate1);
                requestDTO.setStatusCode(200);
                requestDTO.setMessage("Prioridad actualizada con éxito");
                requestDTO.setPrioridad(prioridadUpdate1);
            }, () -> {
                requestDTO.setStatusCode(404);
                requestDTO.setError("Prioridad no encontrada");
            });

        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setError("Ha ocurrido un error inesperado al actualizar la prioridad: " + e.getMessage());
        }
        return requestDTO;
    }

    @Override
    public PrioridadDTO deletePrioridad(Integer idPrioridad) {
        PrioridadDTO requestDTO = new PrioridadDTO();
        try {
            Optional<Prioridad> prioridadDelete = prioridadRepository.findById(idPrioridad);
            prioridadDelete.ifPresentOrElse(prioridadDelete1 -> {
                prioridadRepository.delete(prioridadDelete1);
                requestDTO.setStatusCode(204);
                requestDTO.setMessage("Prioridad eliminada con éxito");
            }, () -> {
                requestDTO.setStatusCode(404);
                requestDTO.setError("Prioridad no encontrada");
            });
        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setError("Ha ocurrido un error inesperado al eliminar la prioridad: " + e.getMessage());
        }
        return requestDTO;
    }

    @Override
    public PrioridadDTO listAllPrioridad() {
        PrioridadDTO response = new PrioridadDTO();

        try {
            List<Object[]> resultTable = prioridadRepository.listAllPrioridad();
            List<PrioridadDTO> listPrioridadDTO = new ArrayList<>();

            if(!resultTable.isEmpty()) {
                for(Object[] objects : resultTable) {
                    PrioridadDTO prioridadDTO = new PrioridadDTO();
                    prioridadDTO.setId((Integer) objects[0]);
                    prioridadDTO.setNombre((String) objects[1]);
                    listPrioridadDTO.add(prioridadDTO);
                }
                response.setStatusCode(200);
                response.setMessage("Se ha listado correctamente la lista de prioridad");
                response.setListAllPrioridad(listPrioridadDTO);
            }
            return response;

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setError("Ha sucedido un error al listar las prioridades: " + e.getMessage());
            return response;
        }
    }
}
