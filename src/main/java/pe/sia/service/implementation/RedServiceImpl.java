package pe.sia.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import pe.sia.persistence.entity.activos.Red;
import pe.sia.persistence.entity.activos.Proveedor;
import pe.sia.persistence.entity.activos.TipoRed;
import pe.sia.persistence.repository.activosRepository.RedRepository;
import pe.sia.persistence.repository.activosRepository.ProveedorRepository;
import pe.sia.persistence.repository.activosRepository.TipoRedRepository;
import pe.sia.presentation.dto.activosDTO.RedDTO;
import pe.sia.service.interfaces.RedService;

/**
 * @author villalta carnero anderson
 * @version 1.0
 */

@Service
public class RedServiceImpl implements RedService {

    private final RedRepository redRepository;
    private final ProveedorRepository proveedorRepository;
    private final TipoRedRepository tipoRedRepository;

    public RedServiceImpl(RedRepository redRepository, ProveedorRepository proveedorRepository, TipoRedRepository tipoRedRepository) {
        this.redRepository = redRepository;
        this.proveedorRepository = proveedorRepository;
        this.tipoRedRepository = tipoRedRepository;
    }

    // Paso 1: Método para crear una nueva Red a través de un DTO
    @Override
    public RedDTO createRed(RedDTO redDTO) {
        Red red = new Red();
        
        red.setModelo(redDTO.getModelo());
        red.setDireccionIP(redDTO.getDireccionIP());
        red.setOrdenCompra(redDTO.getOrdenCompra());
        
        Proveedor proveedor = proveedorRepository.findById(redDTO.getProveedorId())
            .orElseThrow(() -> new RuntimeException("El proveedor no existe"));
        red.setProveedor(proveedor);
        
        TipoRed tipoRed = tipoRedRepository.findById(redDTO.getTipoRedId())
            .orElseThrow(() -> new RuntimeException("El tipo de red no existe"));
        red.setTipoRedId(tipoRed);

        red = redRepository.save(red);

        return mapToDTO(red);
    }

    // Paso 2: Método para actualizar una Red existente a través de un DTO
    @Override
    public RedDTO updateRed(Long idRed, RedDTO redDTO) {
        Red red = redRepository.findById(idRed)
            .orElseThrow(() -> new RuntimeException("La red no existe"));

        red.setModelo(redDTO.getModelo());
        red.setDireccionIP(redDTO.getDireccionIP());
        red.setOrdenCompra(redDTO.getOrdenCompra());
        
        Proveedor proveedor = proveedorRepository.findById(redDTO.getProveedorId())
            .orElseThrow(() -> new RuntimeException("El proveedor no existe"));
        red.setProveedor(proveedor);
        
        TipoRed tipoRed = tipoRedRepository.findById(redDTO.getTipoRedId())
            .orElseThrow(() -> new RuntimeException("El tipo de red no existe"));
        red.setTipoRedId(tipoRed);

        red = redRepository.save(red);

        return mapToDTO(red);
    }

    // Paso 3: Método para eliminar una Red por su ID
    @Override
    public void deleteRed(Long idRed) {
        redRepository.deleteById(idRed);
    }

    // Paso 4: Método para obtener una Red por su ID
    @Override
    public RedDTO getRedById(Long idRed) {
        Red red = redRepository.findById(idRed)
            .orElseThrow(() -> new RuntimeException("La red no existe"));

        return mapToDTO(red);
    }

    // Paso 5: Método para obtener todas las Redes
    @Override
    public List<RedDTO> getAllRedes() {
        return redRepository.findAll()
            .stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }

    // Método auxiliar para mapear un objeto Red a RedDTO
    private RedDTO mapToDTO(Red red) {
        RedDTO dto = new RedDTO();
        dto.setId(red.getId());
        dto.setModelo(red.getModelo());
        dto.setDireccionIP(red.getDireccionIP());
        dto.setProveedorId(red.getProveedor().getId());
        dto.setTipoRedId(red.getTipoRedId().getId());
        dto.setOrdenCompra(red.getOrdenCompra());
        return dto;
    }
}