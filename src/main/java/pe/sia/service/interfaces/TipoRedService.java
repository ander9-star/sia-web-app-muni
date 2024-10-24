package pe.sia.service.interfaces;

import java.util.List;

import pe.sia.presentation.dto.activosDTO.TipoRedDTO;

public interface TipoRedService {

    TipoRedDTO createTipoRed(TipoRedDTO tipoRedDTO);
    TipoRedDTO updateTipoRed(Integer idTipoRed, TipoRedDTO tipoRedDTO);
    void deleteTipoRed(Integer idTipoRed);
    TipoRedDTO getTipoRedById(Integer idTipoRed);
    List<TipoRedDTO> getAllTipoRed();
    
}
