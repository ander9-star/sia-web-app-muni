package pe.sia.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import pe.sia.persistence.repository.activosRepository.ActivoMaestroRepository;
import pe.sia.presentation.dto.activosDTO.ActivoMaestroDTO;
import pe.sia.service.interfaces.ActivoMaestroService;


@Service
public class ActivoMaestroImpl implements ActivoMaestroService {

    private final ActivoMaestroRepository activoMaestroRepository;

    public ActivoMaestroImpl(ActivoMaestroRepository tipoActivoRepository) {
        this.activoMaestroRepository = tipoActivoRepository;
    }

	@Override
	public ActivoMaestroDTO createTipoActivo(ActivoMaestroDTO tipoActivoDTO) {
		throw new UnsupportedOperationException("Unimplemented method 'createTipoActivo'");
	}

	@Override
	public ActivoMaestroDTO updateTipoActivo(Integer idTipoActivo, ActivoMaestroDTO tipoActivoDTO) {
		throw new UnsupportedOperationException("Unimplemented method 'updateTipoActivo'");
	}

	@Override
	public void deleteTipoActivo(Integer idTipoActivo) {
		throw new UnsupportedOperationException("Unimplemented method 'deleteTipoActivo'");
	}

	@Override
	public ActivoMaestroDTO getTipoActivoById(Integer idTipoActivo) {
		throw new UnsupportedOperationException("Unimplemented method 'getTipoActivoById'");
	}

	@Override
	public List<ActivoMaestroDTO> getAllTipoActivo() {
		throw new UnsupportedOperationException("Unimplemented method 'getAllTipoActivo'");
	}

	@Override
	public ActivoMaestroDTO getActivoMaestroSinProblemaGeneral() {
		ActivoMaestroDTO requestDTO = new ActivoMaestroDTO();
		try {
			List<Object[]> resultTable = activoMaestroRepository.getActivoMaestroSinProblemaGeneral();
			List<ActivoMaestroDTO> activoMaestroDTOList = new ArrayList<>();
			if (!resultTable.isEmpty()) {
				for (Object[] objects : resultTable) {
					ActivoMaestroDTO activoMaestroDTO = new ActivoMaestroDTO();
					activoMaestroDTO.setStatusCode(200);
					activoMaestroDTO.setActivoMaestroId((Integer) objects[0]);
					activoMaestroDTO.setCodigoBien((String) objects[1]);
					activoMaestroDTO.setNombreActivo((String) objects[2]);
					activoMaestroDTO.setNombreEmpleado((String) objects[3]);
					activoMaestroDTOList.add(activoMaestroDTO);
				}
				requestDTO.setStatusCode(200);
				requestDTO.setMessage("Listado con éxito la tabla personalizada");
				requestDTO.setActivoMaestroDTOList(activoMaestroDTOList);
			}
			return requestDTO;
		} catch (Exception e) {
			requestDTO.setStatusCode(500);
			requestDTO.setError("Ha ocurrido un error inexperado al listar las problema: " + e.getMessage());
			return requestDTO;
		}
	}

	@Override
	public ActivoMaestroDTO getTop5ActivosIncidencias() {
		ActivoMaestroDTO requestDTO = new ActivoMaestroDTO();
		try {
			List<Object[]> resultTable = activoMaestroRepository.getTop5ActivosIncidencias();
			List<ActivoMaestroDTO> activoMaestroDTOList = new ArrayList<>();
			if(!resultTable.isEmpty()) {
				for(Object[] object : resultTable) {
					ActivoMaestroDTO activoMaestroDTO = new ActivoMaestroDTO.Builder()
							.statusCode(200)
							.codigoBien((String) object[0])
							.nombreActivo((String) object[1])
							.tipoActivo((String) object[2])
							.proveedor((String) object[3])
							.cantidadFI((Integer) object[4])
							.porcentajeFI((Integer) object[5])
							.build();
					activoMaestroDTOList.add(activoMaestroDTO);
				}
				requestDTO.setStatusCode(200);
				requestDTO.setMessage("Se ha extraido con éxito los TOP 5 activos con mas IF");
				requestDTO.setActivoMaestroDTOList(activoMaestroDTOList);
			} else {
				requestDTO.setStatusCode(403);
				requestDTO.setMessage("No hay datos que mostrar");
			}
			return requestDTO;

		} catch (Exception e) {
			requestDTO.setStatusCode(500);
			requestDTO.setError("Ha ocurrido un error inesperado al obtener los TOP 5 activos: " + e.getMessage());
			return requestDTO;
		}
	}


}
