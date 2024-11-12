package pe.sia.service.interfaces;

import java.util.List;
import pe.sia.presentation.dto.activosDTO.InfraestructuraRedDTO;

public interface InfraestructuraRedService {

    InfraestructuraRedDTO createRed(InfraestructuraRedDTO redDTO);
    InfraestructuraRedDTO updateRed(Integer idRed, InfraestructuraRedDTO redDTO);
    void deleteRed(Integer idRed);
    InfraestructuraRedDTO getRedById(Integer idRed);
    List<InfraestructuraRedDTO> getAllRedes();
    
}
