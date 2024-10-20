package pe.sia.service.interfaces;

import java.util.List;

import pe.sia.presentation.dto.activosDTO.RedDTO;

public interface RedService {

    RedDTO createRed(RedDTO redDTO);
    RedDTO updateRed(Long idRed, RedDTO redDTO);
    void deleteRed(Long idRed);
    RedDTO getRedById(Long idRed);
    List<RedDTO> getAllRedes();
    
}
