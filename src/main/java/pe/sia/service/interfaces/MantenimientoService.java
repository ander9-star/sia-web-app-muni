package pe.sia.service.interfaces;

import pe.sia.presentation.dto.problemaDTO.MantenimientoDTO;

import java.util.Map;

public interface MantenimientoService {

    // para eliminar un mantenimiento
    MantenimientoDTO deleteMantenimiento(Integer idMantenimiento);

    // para insertar un nuevo mantenimiento
    MantenimientoDTO insertMantenimiento(MantenimientoDTO mantenimientoDTO);

    // para actualizar el mantenimiento
    MantenimientoDTO updateMantenimiento(Integer idMantenimiento, MantenimientoDTO mantenimientoBody);

    // para obtener el mantenimiento
    MantenimientoDTO getMantenimientoFallo(Integer idFallo);

    // para obtener toda la data de mantenimiento con mas detalles
    MantenimientoDTO getDataMantenimiento();

    // Para obtener aquellas problema/fallos que todavia no tienen mantenimiento
    MantenimientoDTO getFISinMantenimiento ();

    Map<String, Object> getTotalManenimientoHoyAyer();

}
