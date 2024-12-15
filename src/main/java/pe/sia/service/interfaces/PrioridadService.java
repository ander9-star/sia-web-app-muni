package pe.sia.service.interfaces;

import pe.sia.persistence.entity.problema.Prioridad;
import pe.sia.presentation.dto.problemaDTO.PrioridadDTO;

public interface PrioridadService {

    PrioridadDTO insertPrioridad(Prioridad prioridad);
    PrioridadDTO updatePrioridad(Integer idPrioridad, Prioridad prioridad);
    PrioridadDTO deletePrioridad(Integer idPrioridad);
    PrioridadDTO listAllPrioridad();
}
