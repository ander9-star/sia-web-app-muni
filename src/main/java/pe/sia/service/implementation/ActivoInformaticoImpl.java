package pe.sia.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import pe.sia.persistence.entity.activos.ActivoInformatico;
import pe.sia.persistence.entity.activos.Hardware;
import pe.sia.persistence.entity.activos.InfraestructuraRed;
import pe.sia.persistence.entity.activos.Software;
import pe.sia.persistence.repository.activosRepository.ActivoInformaticoRepository;
import pe.sia.persistence.repository.activosRepository.HardwareRepository;
import pe.sia.persistence.repository.activosRepository.InfraestructuraRedRepository;
import pe.sia.persistence.repository.activosRepository.SoftwareRepository;
import pe.sia.presentation.dto.activosDTO.ActivoInformaticoDTO;
import pe.sia.service.interfaces.ActivoInformaticoService;

@Service
public class ActivoInformaticoImpl implements ActivoInformaticoService {

    private final ActivoInformaticoRepository activoInformaticoRepository;
    private final HardwareRepository hardwareRepository;
    private final SoftwareRepository softwareRepository;
    private final InfraestructuraRedRepository redRepository;

    public ActivoInformaticoImpl(ActivoInformaticoRepository activoInformaticoRepository, HardwareRepository hardwareRepository,
                                 SoftwareRepository softwareRepository, InfraestructuraRedRepository redRepository) {
        this.activoInformaticoRepository = activoInformaticoRepository;
        this.hardwareRepository = hardwareRepository;
        this.softwareRepository = softwareRepository;
        this.redRepository = redRepository;
    }

    @Override
    public ActivoInformaticoDTO createActivoInformatico(ActivoInformaticoDTO activoInformaticoDTO) {
       
        ActivoInformatico activoInformatico = new ActivoInformatico();

        // validaciones correspondientes
        // 1. para hardware
        Hardware hardware = hardwareRepository.findById(activoInformaticoDTO.getHardwareId())
                .orElseThrow(() -> new RuntimeException("El hardware no existe"));

        // 2. para software
        Software software = softwareRepository.findById(activoInformaticoDTO.getSoftwareId())
                .orElseThrow(() -> new RuntimeException("El software no existe"));

        // 3. para red
        InfraestructuraRed infraestructuraRed = redRepository.findById(activoInformaticoDTO.getInfraestructuraRedId())
                .orElseThrow(() -> new RuntimeException("El equipo red no existe"));

        activoInformatico.setOrdenCompra(activoInformaticoDTO.getOrdenCompra());
        activoInformatico.setTipoActivo(activoInformaticoDTO.getTipoActivo());
        activoInformatico.setHardware(hardware);
        activoInformatico.setSoftware(software);
        activoInformatico.setInfraestructuraRed(infraestructuraRed);

        activoInformatico = activoInformaticoRepository.save(activoInformatico);

        return mapToDTO(activoInformatico);
    }

    @Override
    public ActivoInformaticoDTO updateActivoInformatico(Integer idactivoInformaticoDTO,
            ActivoInformaticoDTO activoInformaticoDTO) {

        ActivoInformatico activoInformatico = activoInformaticoRepository.findById(activoInformaticoDTO.getId())
                                                    .orElseThrow(() -> new RuntimeException("El activo no existe"));

        // validaciones correspondientes
        // 1. para hardware
        Hardware hardware = hardwareRepository.findById(activoInformaticoDTO.getHardwareId())
                .orElseThrow(() -> new RuntimeException("El hardware no existe"));

        // 2. para software
        Software software = softwareRepository.findById(activoInformaticoDTO.getSoftwareId())
                .orElseThrow(() -> new RuntimeException("El software no existe"));

        // 3. para red
        InfraestructuraRed infraestructuraRed = redRepository.findById(activoInformaticoDTO.getInfraestructuraRedId())
                .orElseThrow(() -> new RuntimeException("El equipo red no existe"));

        activoInformatico.setOrdenCompra(activoInformaticoDTO.getOrdenCompra());
        activoInformatico.setTipoActivo(activoInformaticoDTO.getTipoActivo());
        activoInformatico.setHardware(hardware);
        activoInformatico.setSoftware(software);
        activoInformatico.setInfraestructuraRed(infraestructuraRed);

        activoInformatico = activoInformaticoRepository.save(activoInformatico);

        return mapToDTO(activoInformatico);
    }

    @Override
    public void deleteActivoInformatico(Integer idactivoInformatico) {
        activoInformaticoRepository.deleteById(idactivoInformatico);
    }

    @Override
    public ActivoInformaticoDTO getActivoInformaticoById(Integer idactivoInformatico) {
        ActivoInformatico activoInformatico = activoInformaticoRepository.findById(idactivoInformatico)
                                                    .orElseThrow(() -> new RuntimeException("El activo no existe"));
        return mapToDTO(activoInformatico);
    }

    @Override
    public List<ActivoInformaticoDTO> getAllActivoInformatico() {
        return activoInformaticoRepository.findAll()
                    .stream()
                    .map(this::mapToDTO)
                    .toList();
    }

    private ActivoInformaticoDTO mapToDTO(ActivoInformatico activoInformatico) {
        ActivoInformaticoDTO dto = new ActivoInformaticoDTO();
        dto.setId(activoInformatico.getId());
        dto.setOrdenCompra(activoInformatico.getOrdenCompra());
        dto.setTipoActivo(activoInformatico.getTipoActivo());
        dto.setHardwareId(activoInformatico.getHardware().getId());
        dto.setSoftwareId(activoInformatico.getSoftware().getId());
        dto.setInfraestructuraRedId(activoInformatico.getInfraestructuraRed().getId());
        return dto;
    }

}
