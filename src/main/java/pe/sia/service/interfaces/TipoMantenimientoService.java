package pe.sia.service.interfaces;

import pe.sia.persistence.entity.problema.TipoMantenimiento;
import pe.sia.presentation.dto.problemaDTO.TipoMantenimientoDTO;

public interface TipoMantenimientoService {
    TipoMantenimientoDTO insertTipoMantenimiento(TipoMantenimiento tipoMantenimiento);
    TipoMantenimientoDTO updateTipoMantenimiento(Integer idTipoMantenimiento, TipoMantenimiento tipoMantenimiento);
    TipoMantenimientoDTO deleteTipoMantenimiento(Integer idTipoMantenimiento);
    TipoMantenimientoDTO listAllTipoMantenimiento();
    // Para obtener aquellas solo la data importante para la creacion del mantenimiento
    TipoMantenimientoDTO getDataShortTipoMantenimiento();
}
