package pe.sia.service.interfaces;

import pe.sia.persistence.entity.actores.Rol;
import pe.sia.presentation.dto.actoresDTO.RolDTO;

public interface RolService {
    RolDTO insertRol(Rol rol);
    RolDTO updateRol(Integer idRol, Rol rol);
    RolDTO deleteRol(Integer idRol);
    RolDTO getDataRolPersonal();
    RolDTO getDataRolUser();
}
