package pe.sia.service.interfaces;

import pe.sia.presentation.dto.problemaDTO.AuditoriaDTO;
import java.util.Map;

public interface AuditoriaService {

    // para insertar una auditoria
    AuditoriaDTO insertAuditoria(AuditoriaDTO auditoriaDTO);

    // para actualizar una auditoria
    AuditoriaDTO updateAuditoria(Integer idAuditoria, AuditoriaDTO auditoriaDTO);

    // para eliminar una auditoria
    AuditoriaDTO deleteAuditoria(Integer idAuditoria);

    // para obtener todas las auditorias del mantenimiento si las hay
    AuditoriaDTO getAuditoriaMantenimiento(Integer idMantenimiento);

    // para obtener la data completa de auditoria
    AuditoriaDTO getDataFullAuditoria(Integer idUsuario, Boolean esAdmin);

    // para obtener el total de autorias de hoy y el total que hay en el sistema
    Map<String, Object> getTotalAuditoriaHoyTotal();
}
