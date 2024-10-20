package pe.sia.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import pe.sia.persistence.entity.activos.TipoRed;
import pe.sia.persistence.repository.activosRepository.TipoRedRepository;
import pe.sia.presentation.dto.activosDTO.TipoRedDTO;
import pe.sia.service.interfaces.TipoRedService;

/**
 * @author villalta carnero anderson
 * @version 1.0
 */

@Service
public class TipoRedServiceImpl implements TipoRedService {

    private final TipoRedRepository tipoRedRepository;

    public TipoRedServiceImpl(TipoRedRepository tipoRedRepository) {
        this.tipoRedRepository = tipoRedRepository;
    }

    // Paso 1: Método para crear un nuevo tipo de red a través de un DTO
    @Override
    public TipoRedDTO createTipoRed(TipoRedDTO tipoRedDTO) {
        TipoRed tipoRed = new TipoRed();

        tipoRed.setNombre(tipoRedDTO.getNombre());

        tipoRed = tipoRedRepository.save(tipoRed);

        return mapToDTO(tipoRed);
    }

    // Paso 2: Método para actualizar un tipo de red existente a través de un DTO
    @Override
    public TipoRedDTO updateTipoRed(Long idTipoRed, TipoRedDTO tipoRedDTO) {

        TipoRed tipoRed = tipoRedRepository.findById(idTipoRed)
                                .orElseThrow(() -> new RuntimeException("El tipo de red no existe"));
        
        tipoRed.setNombre(tipoRedDTO.getNombre());

        tipoRed = tipoRedRepository.save(tipoRed);

        return mapToDTO(tipoRed);
    }

    // Paso 3: Método para eliminar un tipo de red por su ID
    @Override
    public void deleteTipoRed(Long idTipoRed) {
        tipoRedRepository.deleteById(idTipoRed);
    }

    // Paso 4: Método para obtener un tipo de red por su ID
    @Override
    public TipoRedDTO getTipoRedById(Long idTipoRed) {
        TipoRed tipoRed = tipoRedRepository.findById(idTipoRed)
                            .orElseThrow(() -> new RuntimeException("El tipo de red no existe"));

        return mapToDTO(tipoRed);
    }

    // Paso 5: Método para obtener todos los tipos de red
    @Override
    public List<TipoRedDTO> getAllTipoRed() {
        return tipoRedRepository.findAll()
                    .stream()
                    .map(this::mapToDTO)
                    .toList();
    }

    // Método auxiliar para mapear un objeto TipoRed a TipoRedDTO
    private TipoRedDTO mapToDTO(TipoRed tipoRed) {
        TipoRedDTO dto = new TipoRedDTO();
        dto.setId(tipoRed.getId());
        dto.setNombre(tipoRed.getNombre());
        return dto;
    }

}