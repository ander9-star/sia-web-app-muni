package pe.sia.service.interfaces;

import java.util.List;
import pe.sia.presentation.dto.activosDTO.ActivoMaestroDTO;

public interface ActivoMaestroService {

    ActivoMaestroDTO createTipoActivo(ActivoMaestroDTO tipoActivoDTO);
    ActivoMaestroDTO updateTipoActivo(Integer idTipoActivo, ActivoMaestroDTO tipoActivoDTO);
    void deleteTipoActivo(Integer idTipoActivo);
    ActivoMaestroDTO getTipoActivoById(Integer idTipoActivo);
    List<ActivoMaestroDTO> getAllTipoActivo();
    // procedimiento para obener los top 5 activos con mas incidenciaas/fallos
    ActivoMaestroDTO getTop5ActivosIncidencias();
    ActivoMaestroDTO getActivoMaestroSinProblemaGeneral();
    
}
