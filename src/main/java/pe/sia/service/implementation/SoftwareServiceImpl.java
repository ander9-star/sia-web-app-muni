package pe.sia.service.implementation;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
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
                .orElseThrow(() -> new RuntimeException("El proveedor no existe en la base de datos"));
        software.setProveedor(proveedor);

        software = softwareRepository.save(software);

        return mapToDTO(software);
    }

    @Override
    public SoftwareDTO updateSoftware(Integer idSoftware, SoftwareDTO softwareDTO) {
        Software software = softwareRepository.findById(idSoftware)
                .orElseThrow(() -> new RuntimeException("El proveedor no existe para actualizar"));
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

    private LocalDate convertirLocalDate(Date date) {
        return date.toLocalDate();
    }

    @Override
    public SoftwareDTO getFISoftware() {
        SoftwareDTO requestDTO = new SoftwareDTO();
        try {
            List<Object[]> resultTable = softwareRepository.getFISoftware();
            List<SoftwareDTO> softwareDTOList = new ArrayList<>();
            if(!resultTable.isEmpty()) {
                for (Object[] row : resultTable) {
                    SoftwareDTO softwareDTO = new SoftwareDTO();
                    softwareDTO.setStatusCode(200);
                    softwareDTO.setNombre((String) row[0]);
                    softwareDTO.setVersion((String) row[1]);
                    softwareDTO.setFechaInstalacion(convertirLocalDate((Date) row[2]));
                    softwareDTO.setFechaVencimientoLicencia(convertirLocalDate((Date) row[3]));
                    softwareDTO.setNombreProveedor((String) row[4]);
                    softwareDTO.setNombreEmpleado((String) row[5]);
                    softwareDTO.setCantidadIncidencias((Integer) row[6]);
                    softwareDTO.setPorcentajeIncidencias((BigDecimal) row[7]);
                    softwareDTOList.add(softwareDTO);
                }
                requestDTO.setStatusCode(200);
                requestDTO.setMessage("Se ha obtenido la data completa de software");
                requestDTO.setSoftwareDTOList(softwareDTOList);
            } else{
                requestDTO.setStatusCode(404);
                requestDTO.setMessage("Lista vacia");
            }
            return requestDTO;
        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setMessage("Ha sucedio un error: " + e.getMessage());
            return requestDTO;
        }
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
