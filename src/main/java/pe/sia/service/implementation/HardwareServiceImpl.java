package pe.sia.service.implementation;

import java.math.BigDecimal;
import java.util.ArrayList;
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

    // Paso 1: Metodo para crear un hardware a través de un DTO
    @Override
    public HardwareDTO createHardware(HardwareDTO hardwareDTO) {
        Hardware hardware = new Hardware();

        hardware.setNombre(hardwareDTO.getNombre());
        hardware.setMarca(hardwareDTO.getMarca());
        hardware.setModelo(hardwareDTO.getModelo());
        hardware.setProveedor(getProveedor(hardwareDTO));

        hardware = hardwareRepository.save(hardware);

        return mapToDTO(hardware);
    }

    // Paso 2: Metodo para actualizar un hardware existente a través de un DTO
    @Override
    public HardwareDTO updateHardware(Integer idHardware, HardwareDTO hardwareDTO) {
        Hardware hardware = hardwareRepository.findById(idHardware)
                                    .orElseThrow(() -> new RuntimeException("Hardware no encontrado"));

        hardware.setNombre(hardwareDTO.getNombre());
        hardware.setMarca(hardwareDTO.getMarca());
        hardware.setModelo(hardwareDTO.getModelo());
        hardware.setProveedor(getProveedor(hardwareDTO));

        hardware = hardwareRepository.save(hardware);
        return mapToDTO(hardware);
    }

    // Paso 3: Metodo para eliminar un hardware por su ID
    @Override
    public void deleteHardware(Integer idHardware) {
        hardwareRepository.deleteById(idHardware); 
    }

    // Paso 4: Metodo para obtener un hardware por su ID
    @Override
    public HardwareDTO getHardwareById(Integer idHardware) {
        Hardware hardware = hardwareRepository
                                    .findById(idHardware)
                                    .orElseThrow(() -> new RuntimeException("Hardware no encontrado"));
        return mapToDTO(hardware);
    }

    // Paso 5: Metodo para obtener todos los hardwares
    @Override
    public List<HardwareDTO> getAllHardware() {
        return hardwareRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public HardwareDTO getFIPorHardware() {
        HardwareDTO requestDTO = new HardwareDTO();
        try {
            List<Object[]> resultTable = hardwareRepository.getFIPorHardware();
            List<HardwareDTO> hardwareDTOList = new ArrayList<>();
            if(!resultTable.isEmpty()) {
                for (Object[] row : resultTable) {
                    HardwareDTO hardwareDTO = new HardwareDTO();
                    hardwareDTO.setNombre((String) row[0]);
                    hardwareDTO.setMarca((String) row[1]);
                    hardwareDTO.setModelo((String) row[2]);
                    hardwareDTO.setNombreProveedor((String) row[3]);
                    hardwareDTO.setNombreEmpleado(!((String) row[4]).trim().isEmpty() ? (String) row[4] : "Sin Asignación");
                    hardwareDTO.setCantidadIncidencias((Integer) row[5]);
                    hardwareDTO.setPorcentajeIncidencias((BigDecimal) row[6]);
                    hardwareDTOList.add(hardwareDTO);
                }
                requestDTO.setStatusCode(200);
                requestDTO.setMessage("Se ha obtenido con éxito la data de hardware");
                requestDTO.setHardwareDTOList(hardwareDTOList);
            } else {
                requestDTO.setStatusCode(404);
                requestDTO.setMessage("Lista vacia");
            }
            return requestDTO;

        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setMessage("Error al obtener la data completa: " + e.getMessage());
            return requestDTO;
        }
    }

    // Metodo auxiliar para mapear un objeto Hardware a HardwareDTO
    private HardwareDTO mapToDTO(Hardware hardware) {
        HardwareDTO dto = new HardwareDTO();
        dto.setId(hardware.getId());
        dto.setNombre(hardware.getNombre());
        dto.setMarca(hardware.getMarca());
        dto.setModelo(hardware.getModelo());
        dto.setProveedorId(hardware.getProveedor().getId());
        return dto;
    }

    // Metodo auxiliar para obtener un Proveedor a partir de un HardwareDTO
    private Proveedor getProveedor(HardwareDTO hardwareDTO) {
        return proveedorRepository
                                .findById(hardwareDTO.getProveedorId())
                                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado: " + hardwareDTO.getProveedorId()));
    }

}
