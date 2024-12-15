package pe.sia.service.implementation;

import org.springframework.stereotype.Service;
import pe.sia.persistence.entity.actores.Personal;
import pe.sia.persistence.repository.actoresRepository.PersonalRepository;
import pe.sia.persistence.repository.actoresRepository.RolRepository;
import pe.sia.presentation.dto.actoresDTO.PersonalDTO;
import pe.sia.service.interfaces.PersonalService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonalServiceImpl implements PersonalService {

    private final PersonalRepository personalRepository;
    private final RolRepository rolRepository;

    public PersonalServiceImpl(PersonalRepository personalRepository, RolRepository rolRepository) {
        this.personalRepository = personalRepository;
        this.rolRepository = rolRepository;
    }

    @Override
    public PersonalDTO getPersonalMantenimiento() {
        PersonalDTO requestDTO = new PersonalDTO();
        try {
            List<Object[]> resultTable = personalRepository.getPersonalMantenimiento();
            List<PersonalDTO> listPersonal = new ArrayList<>();
            if (!resultTable.isEmpty()) {
                for (Object[] row : resultTable) {
                    PersonalDTO personalDTO = new PersonalDTO();
                    personalDTO.setStatusCode(200);
                    personalDTO.setPersonal((String) row[0]);
                    personalDTO.setRol((String) row[1]);
                    personalDTO.setCantidadMantenimiento((Integer) row[2]);
                    personalDTO.setCantidadAuditoria((Integer) row[3]);
                    listPersonal.add(personalDTO);
                }
                requestDTO.setStatusCode(200);
                requestDTO.setMessage("Se ha obtenido la data de personal");
                requestDTO.setPersonalDTOList(listPersonal);
            } else {
                requestDTO.setStatusCode(404);
                requestDTO.setMessage("Lista vacia, no hay datos");
            }
            return requestDTO;
        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setError("Ha ocurrido un error inesperado al obtener la data: " + e.getMessage());
            return requestDTO;
        }
    }

    @Override
    public PersonalDTO getIdNombrepersonal() {
        PersonalDTO requestDTO = new PersonalDTO();
        try {
            List<Object[]> resultTable = personalRepository.getIdNombrepersonal();
            List<PersonalDTO> listPersonal = new ArrayList<>();
            if (!resultTable.isEmpty()) {
                for (Object[] row : resultTable) {
                    PersonalDTO personalDTO = new PersonalDTO();
                    personalDTO.setStatusCode(200);
                    personalDTO.setIdPersonal((Integer) row[0]);
                    personalDTO.setPersonal((String) row[1]);
                    listPersonal.add(personalDTO);
                }
                requestDTO.setStatusCode(200);
                requestDTO.setMessage("Se ha obtenido la data de personal");
                requestDTO.setPersonalDTOList(listPersonal);
            } else {
                requestDTO.setStatusCode(404);
                requestDTO.setMessage("Lista vacia, no hay datos");
            }
            return requestDTO;
        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setError("Ha ocurrido un error inesperado al obtener la data: " + e.getMessage());
            return requestDTO;
        }
    }

    @Override
    public PersonalDTO getFullDataPersonal() {
        PersonalDTO requestDTO = new PersonalDTO();
        try {
            List<Object[]> resultTable = personalRepository.getFullDataPersonal();
            List<PersonalDTO> listPersonal = new ArrayList<>();
            if (!resultTable.isEmpty()) {
                for (Object[] row : resultTable) {
                    PersonalDTO personalDTO = new PersonalDTO();
                    personalDTO.setStatusCode(200);
                    personalDTO.setIdPersonal((Integer) row[0]);
                    personalDTO.setNombre((String) row[1]);
                    personalDTO.setApellidos((String) row[2]);
                    personalDTO.setRolId((Integer) row[3]);
                    personalDTO.setRol((String) row[4]);
                    personalDTO.setEstado(getEstado((Boolean) row[5]));
                    personalDTO.setCantidadMantenimiento((Integer) row[6]);
                    personalDTO.setCantidadAuditoria((Integer) row[7]);
                    listPersonal.add(personalDTO);
                }
                requestDTO.setStatusCode(200);
                requestDTO.setMessage("Se ha obtenido la data completa del personal");
                requestDTO.setPersonalDTOList(listPersonal);
            } else {
                requestDTO.setStatusCode(404);
                requestDTO.setMessage("Lista vacia, no hay datos para obtener");
            }
            return requestDTO;
        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setError("Ha ocurrido un error inesperado al obtener la data completa: " + e.getMessage());
            return requestDTO;
        }
    }

    @Override
    public PersonalDTO updatePersonal(Integer idPersonal, PersonalDTO personalDTO) {
        PersonalDTO requestDTO = new PersonalDTO();
        try {
            Optional<Personal> personal = personalRepository.findById(idPersonal);
            if (personal.isPresent()) {
                Personal personalOld = personal.get();
                personalOld.setNombre(personalDTO.getNombre());
                personalOld.setApellidos(personalDTO.getApellidos());
                personalOld.setRol(rolRepository.findById(personalDTO.getRolId()).orElseThrow());
                personalOld.setEstado(personalDTO.getEstado().equals("Activo"));
                Personal newPersonal = personalRepository.save(personalOld);
                requestDTO.setStatusCode(201);
                requestDTO.setMessage("Se ha actualizado exitosamente el personal");
                requestDTO.setPersonalTI(newPersonal);
            } else {
                requestDTO.setStatusCode(404);
                requestDTO.setMessage("Personal no encontrado");
            }
        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setMessage("Ha ocurrido un error al actualizar el personal: " + e.getMessage());
        }
        return requestDTO;
    }

    private boolean personalTieneMantenimiento(Integer personalId) {
        Optional<Boolean> findPersonal = personalRepository.findMantenimientoPersonal(personalId);
        return findPersonal.isPresent() && Boolean.TRUE.equals(findPersonal.get());
    }

    private PersonalDTO crearRespuestaPersonalConMantenimiento(PersonalDTO requestDTO) {
        requestDTO.setStatusCode(404);
        requestDTO.setMessage("Personal tiene mantenimientos registrados");
        return requestDTO;
    }

    @Override
    public PersonalDTO deletePersonal(Integer idPersonal) {
        PersonalDTO requestDTO = new PersonalDTO();
        try {
            if (personalTieneMantenimiento(idPersonal)) {
                return crearRespuestaPersonalConMantenimiento(requestDTO);
            }
            Optional<Personal> personalOptional = personalRepository.findById(idPersonal);
            if (personalOptional.isPresent()) {
                personalRepository.deleteById(idPersonal);
                requestDTO.setStatusCode(204);
                requestDTO.setMessage("Se ha eliminado correctamente el personal");
            } else {
                requestDTO.setStatusCode(404);
                requestDTO.setMessage("El personal no ha sido encontrado");
            }
        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setError("Ha ocurrido un error inesperado al eliminar el personal: " + e.getMessage());
        }
        return requestDTO;
    }

    @Override
    public PersonalDTO insertPersonal(PersonalDTO personalDTO) {
        PersonalDTO requestDTO = new PersonalDTO();
        try {
            Personal personalNew = new Personal();
            personalNew.setNombre(personalDTO.getNombre());
            personalNew.setApellidos(personalDTO.getApellidos());
            personalNew.setRol(rolRepository.findById(personalDTO.getRolId()).orElseThrow());
            personalNew.setEstado(personalDTO.getEstado().equals("Activo"));
            Personal newPersonal = personalRepository.save(personalNew);
            if (newPersonal.getId() > 0) {
                requestDTO.setStatusCode(201);
                requestDTO.setMessage("Se ha creado exitosamente el personal");
                requestDTO.setPersonalTI(newPersonal);
            }
        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setMessage("Ha ocurrido un error al crear el personal: " + e.getMessage());
        }
        return requestDTO;
    }

    private String getEstado(Boolean estado) {
        return Boolean.TRUE.equals(estado) ? "Activo" : "Inactivo";
    }
}
