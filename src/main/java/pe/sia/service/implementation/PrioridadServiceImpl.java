package pe.sia.service.implementation;

import org.springframework.stereotype.Service;
import pe.sia.persistence.repository.incidenciasRepository.PrioridadRepository;
import pe.sia.presentation.dto.incienciasDTO.PrioridadDTO;
import pe.sia.service.interfaces.PrioridadService;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrioridadServiceImpl implements PrioridadService  {

    private final PrioridadRepository prioridadRepository;

    public PrioridadServiceImpl(PrioridadRepository prioridadRepository) {
        this.prioridadRepository = prioridadRepository;
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
