package pe.sia.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import pe.sia.persistence.entity.activos.TipoActivo;
import pe.sia.persistence.repository.activosRepository.TipoActivoRepository;
import pe.sia.presentation.dto.activosDTO.TipoActivoDTO;
import pe.sia.service.interfaces.TipoActivoService;


@Service
public class TipoActivoServiceImpl implements TipoActivoService {

    private final TipoActivoRepository tipoActivoRepository;

    public TipoActivoServiceImpl(TipoActivoRepository tipoActivoRepository) {
        this.tipoActivoRepository = tipoActivoRepository;
    }

    @Override
    public TipoActivoDTO createTipoActivo(TipoActivoDTO tipoActivoDTO) {
        TipoActivo tipoActivo = new TipoActivo();

        tipoActivo.setNombre(tipoActivoDTO.getNombre());

        tipoActivo = tipoActivoRepository.save(tipoActivo);

        return mapToDTO(tipoActivo);
    }

    @Override
    public TipoActivoDTO updateTipoActivo(Integer idTipoActivo, TipoActivoDTO tipoActivoDTO) {
        TipoActivo tipoActivo = tipoActivoRepository.findById(idTipoActivo)
                                    .orElseThrow(() -> new RuntimeException("el tipo de activo no existe"));
        tipoActivo.setNombre(tipoActivoDTO.getNombre());

        tipoActivo = tipoActivoRepository.save(tipoActivo);

        return mapToDTO(tipoActivo);
    }

    @Override
    public void deleteTipoActivo(Integer idTipoActivo) {
        tipoActivoRepository.deleteById(idTipoActivo);
    }

    @Override
    public TipoActivoDTO getTipoActivoById(Integer idTipoActivo) {
        TipoActivo tipoActivo = tipoActivoRepository.findById(idTipoActivo)
                                    .orElseThrow(() -> new RuntimeException("el tipo de activo no existe"));
        return mapToDTO(tipoActivo);
    }

    @Override
    public List<TipoActivoDTO> getAllTipoActivo() {
       return tipoActivoRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    private TipoActivoDTO mapToDTO(TipoActivo tipoActivo) {
        TipoActivoDTO dto = new TipoActivoDTO();
        dto.setId(tipoActivo.getId());
        dto.setNombre(tipoActivo.getNombre());
        return dto;
    }
    

}
