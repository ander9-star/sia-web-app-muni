package pe.sia.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import pe.sia.persistence.entity.activos.InfraestructuraRed;
import pe.sia.persistence.entity.activos.Proveedor;
import pe.sia.persistence.entity.activos.TipoRed;
import pe.sia.persistence.repository.activosRepository.InfraestructuraRedRepository;
import pe.sia.persistence.repository.activosRepository.ProveedorRepository;
import pe.sia.persistence.repository.activosRepository.TipoRedRepository;
import pe.sia.presentation.dto.activosDTO.InfraestructuraRedDTO;
import pe.sia.service.interfaces.InfraestructuraRedService;

/**
 * @author villalta carnero anderson
 * @version 1.0
 */

@Service
public class InfraestructuraRedServiceImpl implements InfraestructuraRedService {

    private final InfraestructuraRedRepository redRepository;
    private final ProveedorRepository proveedorRepository;
    private final TipoRedRepository tipoRedRepository;

    public InfraestructuraRedServiceImpl(InfraestructuraRedRepository redRepository, ProveedorRepository proveedorRepository, TipoRedRepository tipoRedRepository) {
        this.redRepository = redRepository;
        this.proveedorRepository = proveedorRepository;
        this.tipoRedRepository = tipoRedRepository;
    }

    // Paso 1: Método para crear una nueva Red a través de un DTO
    @Override
    public InfraestructuraRedDTO createRed(InfraestructuraRedDTO redDTO) {
        InfraestructuraRed red = new InfraestructuraRed();

        TipoRed tipoRed = tipoRedRepository.findById(redDTO.getTipoRedId())
        .orElseThrow(() -> new RuntimeException("El tipo de red no existe"));
        red.setTipoRed(tipoRed);

        red.setDireccionIP(redDTO.getDireccionIP());

        Proveedor proveedor = proveedorRepository.findById(redDTO.getProveedorId())
            .orElseThrow(() -> new RuntimeException("El proveedor no existe"));
        red.setProveedor(proveedor);
        
        red = redRepository.save(red);

        return mapToDTO(red);
    }

    // Paso 2: Método para actualizar una Red existente a través de un DTO
    @Override
    public InfraestructuraRedDTO updateRed(Integer idRed, InfraestructuraRedDTO redDTO) {
        InfraestructuraRed red = redRepository.findById(idRed)
            .orElseThrow(() -> new RuntimeException("La red no existe"));

        TipoRed tipoRed = tipoRedRepository.findById(redDTO.getTipoRedId())
            .orElseThrow(() -> new RuntimeException("El tipo de red no existe"));
        red.setTipoRed(tipoRed);

        red.setDireccionIP(redDTO.getDireccionIP());

        Proveedor proveedor = proveedorRepository.findById(redDTO.getProveedorId())
            .orElseThrow(() -> new RuntimeException("El proveedor no existe"));
        red.setProveedor(proveedor);
        

        red = redRepository.save(red);

        return mapToDTO(red);
    }

    // Paso 3: Método para eliminar una Red por su ID
    @Override
    public void deleteRed(Integer idRed) {
        redRepository.deleteById(idRed);
    }

    // Paso 4: Método para obtener una Red por su ID
    @Override
    public InfraestructuraRedDTO getRedById(Integer idRed) {
        InfraestructuraRed red = redRepository.findById(idRed)
            .orElseThrow(() -> new RuntimeException("La red no existe"));

        return mapToDTO(red);
    }

    // Paso 5: Método para obtener todas las Redes
    @Override
    public List<InfraestructuraRedDTO> getAllRedes() {
        return redRepository.findAll()
            .stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }

    // Método auxiliar para mapear un objeto Red a RedDTO
    private InfraestructuraRedDTO mapToDTO(InfraestructuraRed red) {
        InfraestructuraRedDTO dto = new InfraestructuraRedDTO();
        dto.setId(red.getId());
        dto.setTipoRedId(red.getTipoRed().getId());
        dto.setDireccionIP(red.getDireccionIP());
        dto.setProveedorId(red.getProveedor().getId());
        return dto;
    }
}