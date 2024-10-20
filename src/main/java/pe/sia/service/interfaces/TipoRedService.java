package pe.sia.service.interfaces;

import java.util.List;

import pe.sia.presentation.dto.activosDTO.TipoRedDTO;

public interface TipoRedService {

    TipoRedDTO createTipoRed(TipoRedDTO tipoRedDTO);
    TipoRedDTO updateTipoRed(Long idTipoRed, TipoRedDTO tipoRedDTO);
    void deleteTipoRed(Long idTipoRed);
    TipoRedDTO getTipoRedById(Long idTipoRed);
    List<TipoRedDTO> getAllTipoRed();
    
}
