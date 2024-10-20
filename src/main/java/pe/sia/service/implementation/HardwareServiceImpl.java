package pe.sia.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import pe.sia.persistence.entity.activos.Hardware;
import pe.sia.persistence.entity.activos.Proveedor;
import pe.sia.persistence.repository.activosRepository.HardwareRepository;
import pe.sia.persistence.repository.activosRepository.ProveedorRepository;
import pe.sia.presentation.dto.activosDTO.HardwareDTO;
import pe.sia.service.interfaces.HardwareService;
import java.util.stream.Collectors;

/**
 * @author villalta carnero anderson
 * @version 1.0
 */

@Service
public class HardwareServiceImpl implements HardwareService {

    private final HardwareRepository hardwareRepository;
    private final ProveedorRepository proveedorRepository;

    public HardwareServiceImpl(HardwareRepository hardwareRepository, ProveedorRepository proveedorRepository) {
        this.hardwareRepository = hardwareRepository;
        this.proveedorRepository = proveedorRepository;
    }

    // Paso 1: Método para crear un hardware a través de un DTO
    @Override
    public HardwareDTO createHardware(HardwareDTO hardwareDTO) {
        Hardware hardware = new Hardware();

        hardware.setNombre(hardwareDTO.getNombre());
        hardware.setMarca(hardwareDTO.getMarca());
        hardware.setModelo(hardwareDTO.getModelo());
        hardware.setOrdenCompra(hardwareDTO.getOrdenCompra());
        hardware.setProveedor(getProveedor(hardwareDTO));

        hardware = hardwareRepository.save(hardware);

        return mapToDTO(hardware);
    }

    // Paso 2: Método para actualizar un hardware existente a través de un DTO
    @Override
    public HardwareDTO updateHardware(Long idHardware, HardwareDTO hardwareDTO) {
        Hardware hardware = hardwareRepository.findById(idHardware)
                                    .orElseThrow(() -> new RuntimeException("Hardware no encontrado"));

        hardware.setNombre(hardwareDTO.getNombre());
        hardware.setMarca(hardwareDTO.getMarca());
        hardware.setModelo(hardwareDTO.getModelo());
        hardware.setOrdenCompra(hardwareDTO.getOrdenCompra());
        hardware.setProveedor(getProveedor(hardwareDTO));

        hardware = hardwareRepository.save(hardware);
        return mapToDTO(hardware);
    }

    // Paso 3: Método para eliminar un hardware por su ID
    @Override
    public void deleteHardware(Long idHardware) {
        hardwareRepository.deleteById(idHardware); 
    }

    // Paso 4: Método para obtener un hardware por su ID
    @Override
    public HardwareDTO getHardwareById(Long idHardware) {
        Hardware hardware = hardwareRepository
                                    .findById(idHardware)
                                    .orElseThrow(() -> new RuntimeException("Hardware no encontrado"));
        return mapToDTO(hardware);
    }

    // Paso 5: Método para obtener todos los hardwares
    @Override
    public List<HardwareDTO> getAllHardware() {
        return hardwareRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Método auxiliar para mapear un objeto Hardware a HardwareDTO
    private HardwareDTO mapToDTO(Hardware hardware) {
        HardwareDTO dto = new HardwareDTO();
        dto.setId(hardware.getId());
        dto.setNombre(hardware.getNombre());
        dto.setMarca(hardware.getMarca());
        dto.setModelo(hardware.getModelo());
        dto.setProveedorId(hardware.getProveedor().getId());
        dto.setOrdenCompra(hardware.getOrdenCompra());
        return dto;
    }

    // Método auxiliar para obtener un Proveedor a partir de un HardwareDTO
    private Proveedor getProveedor(HardwareDTO hardwareDTO) {
        return proveedorRepository
                                .findById(hardwareDTO.getProveedorId())
                                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado: " + hardwareDTO.getProveedorId()));
    }

}
