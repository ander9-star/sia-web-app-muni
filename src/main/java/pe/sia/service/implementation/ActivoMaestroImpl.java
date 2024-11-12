package pe.sia.service.implementation;

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
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'createTipoActivo'");
	}

	@Override
	public ActivoMaestroDTO updateTipoActivo(Integer idTipoActivo, ActivoMaestroDTO tipoActivoDTO) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'updateTipoActivo'");
	}

	@Override
	public void deleteTipoActivo(Integer idTipoActivo) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'deleteTipoActivo'");
	}

	@Override
	public ActivoMaestroDTO getTipoActivoById(Integer idTipoActivo) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getTipoActivoById'");
	}

	@Override
	public List<ActivoMaestroDTO> getAllTipoActivo() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getAllTipoActivo'");
	}


}
