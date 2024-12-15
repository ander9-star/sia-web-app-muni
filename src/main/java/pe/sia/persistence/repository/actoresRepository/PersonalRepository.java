package pe.sia.persistence.repository.actoresRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.sia.persistence.entity.actores.Personal;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonalRepository extends JpaRepository<Personal, Integer> {

    // listar el personal con la cantidad de mantenimiento y auditorias hechas
    @Query(value = "select * from listar_personal()", nativeQuery = true)
    List<Object[]> getPersonalMantenimiento();

    // listar solo el id y nombre del personal para la creacion de mantenimiento
    @Query(value = "select * from get_data_importante_personal()", nativeQuery = true)
    List<Object[]> getIdNombrepersonal();

    // listar la data completa para el personal
    @Query(value = "select * from listar_full_data_personal()", nativeQuery = true)
    List<Object[]> getFullDataPersonal();

    // para verificar que si el personal a eliminar tiene mantenimientos a cargo
    @Query(value = "select existe_personal_mantenimiento(:id_personal)", nativeQuery = true)
    Optional<Boolean> findMantenimientoPersonal(@Param("id_personal") Integer idPersonal);
}
