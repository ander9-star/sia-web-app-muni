package pe.sia.service.interfaces;

import pe.sia.presentation.dto.actoresDTO.EmpleadoDTO;

public interface EmpleadoService {

    // para obtener los 5 empleados con mas problema y su porcentaje
    EmpleadoDTO getTop5Empleados();

    // para obtener todos los empleados y la cantidad total de problema
    EmpleadoDTO getTotalEmpleadosIncidencias();

    // para obtener la data completa de empleados
    EmpleadoDTO getDataTotalEmpleados();

}
