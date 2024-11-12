package pe.sia.service.interfaces;

import java.util.List;

import pe.sia.presentation.dto.activosDTO.ActivoMaestroDTO;

public interface ActivoMaestroService {

    ActivoMaestroDTO createTipoActivo(ActivoMaestroDTO tipoActivoDTO);
    ActivoMaestroDTO updateTipoActivo(Integer idTipoActivo, ActivoMaestroDTO tipoActivoDTO);
    void deleteTipoActivo(Integer idTipoActivo);
    ActivoMaestroDTO getTipoActivoById(Integer idTipoActivo);
    List<ActivoMaestroDTO> getAllTipoActivo();
    
}
