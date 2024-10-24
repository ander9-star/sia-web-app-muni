package pe.sia.service.interfaces;

import java.util.List;

import pe.sia.presentation.dto.activosDTO.TipoActivoDTO;

public interface TipoActivoService {

    TipoActivoDTO createTipoActivo(TipoActivoDTO tipoActivoDTO);
    TipoActivoDTO updateTipoActivo(Integer idTipoActivo, TipoActivoDTO tipoActivoDTO);
    void deleteTipoActivo(Integer idTipoActivo);
    TipoActivoDTO getTipoActivoById(Integer idTipoActivo);
    List<TipoActivoDTO> getAllTipoActivo();
    
}
