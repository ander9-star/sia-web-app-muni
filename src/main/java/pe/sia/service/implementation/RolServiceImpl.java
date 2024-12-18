package pe.sia.service.implementation;

import org.springframework.stereotype.Service;
import pe.sia.persistence.entity.actores.Rol;
import pe.sia.persistence.repository.actoresRepository.RolRepository;
import pe.sia.presentation.dto.actoresDTO.RolDTO;
import pe.sia.service.interfaces.RolService;
import java.util.List;
import java.util.Optional;

@Service
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;

    public RolServiceImpl(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Override
    public RolDTO insertRol(Rol rol) {
        RolDTO requestDTO = new RolDTO();
        try {
            Rol rolNew = new Rol();
            rolNew.setNombre(rol.getNombre());
            Rol newRol = rolRepository.save(rolNew);
            if(newRol.getId() > 0) {
                requestDTO.setStatusCode(201);
                requestDTO.setMessage("Rol creado con éxito");
                requestDTO.setRol(newRol);
            }
        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setError("Ha ocurrido un error inesperado al crear el rol: " + e.getMessage());
        }
        return requestDTO;
    }

    @Override
    public RolDTO updateRol(Integer idRol, Rol rol) {
        RolDTO requestDTO = new RolDTO();
        try {
            Optional<Rol> optionalRol = rolRepository.findById(idRol);
            optionalRol.ifPresentOrElse((rolUpdate) -> {
                rolUpdate.setNombre(rol.getNombre());
                rolRepository.save(rolUpdate);
                requestDTO.setStatusCode(200);
                requestDTO.setMessage("Rol actualizado con éxito");
                requestDTO.setRol(rolUpdate);
            }, () -> {
                requestDTO.setStatusCode(404);
                requestDTO.setMessage("Rol no encontrado");
            });

        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setError("Ha ocurrido un error inesperado al actualizar el rol: " + e.getMessage());
        }
        return requestDTO;
    }

    @Override
    public RolDTO deleteRol(Integer idRol) {
        RolDTO requestDTO = new RolDTO();
        try {
            Optional<Rol> optionalRol = rolRepository.findById(idRol);
            optionalRol.ifPresentOrElse((rolDelete) -> {
                rolRepository.delete(rolDelete);
                requestDTO.setStatusCode(204);
                requestDTO.setMessage("Rol eliminado con éxito");
            }, () -> {
                requestDTO.setStatusCode(404);
                requestDTO.setMessage("Rol no encontrado");
            });
        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setError("Ha ocurrido un error inesperado al eliminar el rol: " + e.getMessage());
        }
        return requestDTO;
    }

    @Override
    public RolDTO getDataRolPersonal() {
        RolDTO requestDTO = new RolDTO();
        try {
            List<Rol> listRol = rolRepository.findAll().stream().filter( rol -> rol.getId() != 1 && rol.getId() != 2).toList();
            if(!listRol.isEmpty()) {
                requestDTO.setStatusCode(200);
                requestDTO.setMessage("Se ha extraido la data de rol");
                requestDTO.setListRol(listRol);
            } else {
                requestDTO.setStatusCode(2403);
                requestDTO.setMessage("Lista vacia");
            }
            return requestDTO;
        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setMessage("Ha sucedido un erro: " + e.getMessage());
            return requestDTO;
        }
    }

    @Override
    public RolDTO getDataRolUser() {
        RolDTO requestDTO = new RolDTO();
        try {
            List<Rol> listRol = rolRepository.findAll().stream().filter( rol -> rol.getId() <= 1 || rol.getId() == 2).toList();
            if(!listRol.isEmpty()) {
                requestDTO.setStatusCode(200);
                requestDTO.setMessage("Se ha extraido la data de rol");
                requestDTO.setListRol(listRol);
            } else {
                requestDTO.setStatusCode(2403);
                requestDTO.setMessage("Lista vacia");
            }
            return requestDTO;
        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setMessage("Ha sucedido un erro: " + e.getMessage());
            return requestDTO;
        }
    }
}
