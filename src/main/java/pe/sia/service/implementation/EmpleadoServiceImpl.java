package pe.sia.service.implementation;

import org.springframework.stereotype.Service;
import pe.sia.persistence.repository.actoresRepository.EmpleadoRepository;
import pe.sia.presentation.dto.actoresDTO.EmpleadoDTO;
import pe.sia.service.interfaces.EmpleadoService;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public EmpleadoDTO getTop5EmpleadosFallos() {
        EmpleadoDTO requestDTO = new EmpleadoDTO();
        try {
            List<Object[]> resultTable = empleadoRepository.getTop5EmpleadosTncidencias();
            List<EmpleadoDTO> listEmpleado = new ArrayList<>();
            if(!resultTable.isEmpty()) {
                for(Object[] object : resultTable) {
                    EmpleadoDTO empleado = new EmpleadoDTO();
                    empleado.setStatusCode(200);
                    empleado.setNombreEmpleado((String) object[0]);
                    empleado.setPorcentaje((Integer) object[1]);
                    empleado.setCantidad((Integer) object[2]);
                    listEmpleado.add(empleado);
                }
                requestDTO.setStatusCode(200);
                requestDTO.setMessage("Se ha obtenido TOP 5 empleados con éxito");
                requestDTO.setListTop5Empleados(listEmpleado);
            }
            return requestDTO;
        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setMessage("Error al obtener TOP 5 empleados " + e.getMessage());
            return requestDTO;
        }
    }

    @Override
    public EmpleadoDTO getTotalEmpleadosIncidencias() {
        EmpleadoDTO requestDTO = new EmpleadoDTO();
        try {
            List<Object[]> resultTable = empleadoRepository.getTotalEmpleadosIncidencias();
            List<EmpleadoDTO> listEmpleado = new ArrayList<>();
            if(!resultTable.isEmpty()) {
                for(Object[] object : resultTable) {
                    EmpleadoDTO empleado = new EmpleadoDTO.Builder()
                            .statusCode(200)
                            .nombreEmpleado((String) object[0])
                            .cargo((String) object[1])
                            .oficina((String) object[2])
                            .porcentaje((Integer) object[3])
                            .cantidad((Integer) object[4])
                            .build();
                    listEmpleado.add(empleado);
                }
                requestDTO.setStatusCode(200);
                requestDTO.setMessage("Se ha obtenido los empleados con éxito");
                requestDTO.setListEmpleados(listEmpleado);
            }
            return requestDTO;
        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setMessage("Error al obtener los empleados " + e.getMessage());
            return requestDTO;
        }
    }

    @Override
    public EmpleadoDTO getDataTotalEmpleados() {
        EmpleadoDTO requestDTO = new EmpleadoDTO();
        try {
            List<Object[]> resultTable = empleadoRepository.getDataTotalEmpleados();
            List<EmpleadoDTO> listEmpleado = new ArrayList<>();
            if(!resultTable.isEmpty()) {
                for(Object[] object : resultTable) {
                    EmpleadoDTO empleado = new EmpleadoDTO.Builder()
                            .statusCode(200)
                            .codigo((String) object[0])
                            .nombreEmpleado((String) object[1])
                            .cargo((String) object[2])
                            .oficina((String) object[3])
                            .oficinaGerencia((String) object[4])
                            .porcentaje((Integer) object[5])
                            .cantidad((Integer) object[6])
                            .build();
                    listEmpleado.add(empleado);
                }
                requestDTO.setStatusCode(200);
                requestDTO.setMessage("Se ha obtenido la data completa de empleados con éxito");
                requestDTO.setListEmpleados(listEmpleado);
            }
            return requestDTO;
        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setMessage("Error al obtener la data de empleados " + e.getMessage());
            return requestDTO;
        }
    }


}
