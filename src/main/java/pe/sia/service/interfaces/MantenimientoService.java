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

    // para obtener el mantenimiento de acuerdo al id de detalle problema
    MantenimientoDTO getMantenimientoDetalleProblema(Integer idDetalleProblema);

    // para obtener toda la data de mantenimiento con mas detalles
    MantenimientoDTO getDataMantenimiento(Integer idUsuario, Boolean esAdmin);

    // Para obtener aquellas problema/fallos que todavia no tienen mantenimiento
    MantenimientoDTO getFISinMantenimiento ();

    Map<String, Object> getTotalManenimientoHoyAyer();

    Map<String, Object> getTotalVencidaMantenimiento();

}
