package pe.sia.service.interfaces;

import pe.sia.presentation.dto.actoresDTO.PersonalDTO;

public interface PersonalService {

    // listar el personal con la cantidad de mantenimiento y auditorias hechas
    PersonalDTO getPersonalMantenimiento();

    // listar el personal con su id y nombre para la creacion de mantenimiento
    PersonalDTO getIdNombrepersonal();

    // para listar la data completa del personal
    PersonalDTO getFullDataPersonal();

    // para actualizar el personal
    PersonalDTO updatePersonal(Integer idPersonal, PersonalDTO personalDTO);

    // para eliminar el personal
    PersonalDTO deletePersonal(Integer idPersonal);

    // para insertar un nuevo personal
    PersonalDTO insertPersonal(PersonalDTO personalDTO);
}
