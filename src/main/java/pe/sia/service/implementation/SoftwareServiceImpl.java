package pe.sia.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;
import pe.sia.persistence.entity.activos.Proveedor;
import pe.sia.persistence.entity.activos.Software;
import pe.sia.persistence.repository.activosRepository.ProveedorRepository;
import pe.sia.persistence.repository.activosRepository.SoftwareRepository;
import pe.sia.presentation.dto.activosDTO.SoftwareDTO;
import pe.sia.service.interfaces.SoftwareService;

@Service
public class SoftwareServiceImpl implements SoftwareService {

    private final SoftwareRepository softwareRepository;
    private final ProveedorRepository proveedorRepository;

    public SoftwareServiceImpl(SoftwareRepository softwareRepository, ProveedorRepository proveedorRepository) {
        this.softwareRepository = softwareRepository;
        this.proveedorRepository = proveedorRepository;
    }

    @Override
    public SoftwareDTO createSoftware(SoftwareDTO softwareDTO) {
        Software software = new Software();
        software.setNombre(softwareDTO.getNombre());
        software.setVersion(softwareDTO.getVersion());
        software.setFechaInstalacion(softwareDTO.getFechaInstalacion());
        software.setFechaVencimientoLicencia(softwareDTO.getFechaVencimientoLicencia());

        Proveedor proveedor = proveedorRepository.findById(softwareDTO.getProveedorId())
                .orElseThrow(() -> new RuntimeException("El proveedor no existe"));
        software.setProveedor(proveedor);

        software = softwareRepository.save(software);

        return mapToDTO(software);
    }

    @Override
    public SoftwareDTO updateSoftware(Integer idSoftware, SoftwareDTO softwareDTO) {
        Software software = softwareRepository.findById(idSoftware)
                .orElseThrow(() -> new RuntimeException("El proveedor no existe"));
        software.setNombre(softwareDTO.getNombre());
        software.setVersion(softwareDTO.getVersion());
        software.setFechaInstalacion(softwareDTO.getFechaInstalacion());
        software.setFechaVencimientoLicencia(softwareDTO.getFechaVencimientoLicencia());

        Proveedor proveedor = proveedorRepository.findById(softwareDTO.getProveedorId())
                .orElseThrow(() -> new RuntimeException("El proveedor no existe"));
        software.setProveedor(proveedor);

        software = softwareRepository.save(software);

        return mapToDTO(software);
    }

    @Override
    public void deleteSoftware(Integer idSoftware) {
        softwareRepository.deleteById(idSoftware);
    }

    @Override
    public SoftwareDTO getSoftwareById(Integer idSoftware) {
        Software software = softwareRepository.findById(idSoftware)
                                .orElseThrow(() -> new RuntimeException("El proveedor no existe"));
        return mapToDTO(software);
    }

    @Override
    public List<SoftwareDTO> getAllSoftwares() {
        return softwareRepository.findAll()
                    .stream()
                    .map(this::mapToDTO)
                    .toList();
    }

    private SoftwareDTO mapToDTO(Software software) {
        SoftwareDTO dto = new SoftwareDTO();
        dto.setId(software.getId());
        dto.setNombre(software.getNombre());
        dto.setVersion(software.getVersion());
        dto.setFechaInstalacion(software.getFechaInstalacion());
        dto.setFechaVencimientoLicencia(software.getFechaVencimientoLicencia());
        dto.setProveedorId(software.getProveedor().getId());
        return dto;
    }

}
