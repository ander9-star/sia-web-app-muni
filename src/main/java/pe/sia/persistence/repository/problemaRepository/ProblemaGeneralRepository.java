package pe.sia.persistence.repository.problemaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.sia.persistence.entity.problema.ProblemaGeneral;
import java.util.List;
import java.util.Map;

@Repository
public interface ProblemaGeneralRepository extends JpaRepository<ProblemaGeneral, Integer> {

    @Query(value = "select get_problemas_generales_al_mes_actual()", nativeQuery = true)
    Integer getProblemaGeneralAlMesActual();

    // para obtener la cantidad de problema generados en el mes actual y anterior
    @Query(value = "select * from get_problemas_generales_para_mes_actual_anterior()", nativeQuery = true)
    List<Map<String, Object>> getIncidenciasComparacion();

    // para obtener la cantidad de problema general generados en el mes actual
    @Query(value = "select get_problemas_generado_por_dia()", nativeQuery = true)
    Integer countIncidenciasByDay();

    // obteninedo la cantidad de problema generados del dia actual con el anterior
    @Query(value = "select * from get_problema_generado_dia_actual_anterior()", nativeQuery = true)
    List<Map<String, Object>> getIncidenciasDiaComparacion();

    // para obtener la cantidad de problema general generados en el mes actual
    @Query(value = "select get_problemas_generado_por_dia()", nativeQuery = true)
    Integer getProblemaGeneralPorMes();

    @Query(value = "select * from get_data_problema_general(:id_pg, :id_usuario, :es_admin)", nativeQuery = true)
    List<Object[]> getDataNormalizadaProblemaGeneral(@Param("id_pg") Integer idProblemaGeneral, @Param("id_usuario") Integer idUsuario, @Param("es_admin") Boolean esAdmin);

}
