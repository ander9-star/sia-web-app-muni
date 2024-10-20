package pe.sia.service.interfaces;

import java.util.List;

import pe.sia.presentation.dto.activosDTO.TipoActivoDTO;

public interface TipoActivoService {

    TipoActivoDTO createTipoActivo(TipoActivoDTO tipoActivoDTO);
    TipoActivoDTO updateTipoActivo(Long idTipoActivo, TipoActivoDTO tipoActivoDTO);
    void deleteTipoActivo(Long idTipoActivo);
    TipoActivoDTO getTipoActivoById(Long idTipoActivo);
    List<TipoActivoDTO> getAllTipoActivo();
    
}
