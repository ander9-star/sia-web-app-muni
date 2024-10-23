package pe.sia.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import pe.sia.persistence.entity.activos.ActivoInformatico;
import pe.sia.persistence.entity.activos.Hardware;
import pe.sia.persistence.entity.activos.Red;
import pe.sia.persistence.entity.activos.Software;
import pe.sia.persistence.entity.activos.TipoActivo;
import pe.sia.persistence.entity.actores.Empleado;
import pe.sia.persistence.repository.activosRepository.ActivoInformaticoRepository;
import pe.sia.persistence.repository.activosRepository.HardwareRepository;
import pe.sia.persistence.repository.activosRepository.RedRepository;
import pe.sia.persistence.repository.activosRepository.SoftwareRepository;
import pe.sia.persistence.repository.activosRepository.TipoActivoRepository;
import pe.sia.persistence.repository.actoresRepository.EmpleadoRepository;
import pe.sia.presentation.dto.activosDTO.ActivoInformaticoDTO;
import pe.sia.service.interfaces.ActivoInformaticoService;

@Service
public class ActivoInformaticoImpl implements ActivoInformaticoService {

    private final ActivoInformaticoRepository activoInformaticoRepository;
    private final TipoActivoRepository tipoActivoRepository;
    private final EmpleadoRepository empleadoRepository;
    private final HardwareRepository hardwareRepository;
    private final SoftwareRepository softwareRepository;
    private final RedRepository redRepository;

    public ActivoInformaticoImpl(ActivoInformaticoRepository activoInformaticoRepository,
            TipoActivoRepository tipoActivoRepository, EmpleadoRepository empleadoRepository,
            HardwareRepository hardwareRepository, SoftwareRepository softwareRepository, RedRepository redRepository) {
        this.activoInformaticoRepository = activoInformaticoRepository;
        this.tipoActivoRepository = tipoActivoRepository;
        this.empleadoRepository = empleadoRepository;
        this.hardwareRepository = hardwareRepository;
        this.softwareRepository = softwareRepository;
        this.redRepository = redRepository;
    }

    @Override
    public ActivoInformaticoDTO createActivoInformatico(ActivoInformaticoDTO activoInformaticoDTO) {
        ActivoInformatico activoInformatico = new ActivoInformatico();

        // validaciones correspondientes
        // 1. para tipo de activo
        TipoActivo tipoActivo = tipoActivoRepository.findById(activoInformaticoDTO.getTipoActivoId())
                .orElseThrow(() -> new RuntimeException("El tipo de activo no existe"));
        // 2. para empleado
        Empleado empleado = empleadoRepository.findById(activoInformaticoDTO.getEmpleadoId())
                .orElseThrow(() -> new RuntimeException("El empleado no existe"));

        // 3. para hardware
        Hardware hardware = hardwareRepository.findById(activoInformaticoDTO.getHardwareId())
                .orElseThrow(() -> new RuntimeException("El hardware no existe"));

        // 4. para software
        Software software = softwareRepository.findById(activoInformaticoDTO.getSoftwareId())
                .orElseThrow(() -> new RuntimeException("El software no existe"));

        // 5. para red
        Red red = redRepository.findById(activoInformaticoDTO.getRedId())
                .orElseThrow(() -> new RuntimeException("El equipo red no existe"));

        activoInformatico.setTipoActivoId(tipoActivo);
        activoInformatico.setEmpleadoId(empleado);
        activoInformatico.setHardwareId(hardware);
        activoInformatico.setSoftwareId(software);
        activoInformatico.setRedId(red);

        activoInformatico = activoInformaticoRepository.save(activoInformatico);

        return mapToDTO(activoInformatico);
    }

    @Override
    public ActivoInformaticoDTO updateActivoInformatico(Long IdactivoInformaticoDTO,
            ActivoInformaticoDTO activoInformaticoDTO) {

        ActivoInformatico activoInformatico = activoInformaticoRepository.findById(activoInformaticoDTO.getId())
                                                    .orElseThrow(() -> new RuntimeException("El activo no existe"));

        // validaciones correspondientes
        // 1. para tipo de activo
        TipoActivo tipoActivo = tipoActivoRepository.findById(activoInformaticoDTO.getTipoActivoId())
                .orElseThrow(() -> new RuntimeException("El tipo de activo no existe"));
        // 2. para empleado
        Empleado empleado = empleadoRepository.findById(activoInformaticoDTO.getEmpleadoId())
                .orElseThrow(() -> new RuntimeException("El empleado no existe"));

        // 3. para hardware
        Hardware hardware = hardwareRepository.findById(activoInformaticoDTO.getHardwareId())
                .orElseThrow(() -> new RuntimeException("El hardware no existe"));

        // 4. para software
        Software software = softwareRepository.findById(activoInformaticoDTO.getSoftwareId())
                .orElseThrow(() -> new RuntimeException("El software no existe"));

        // 5. para red
        Red red = redRepository.findById(activoInformaticoDTO.getRedId())
                .orElseThrow(() -> new RuntimeException("El equipo red no existe"));

        activoInformatico.setTipoActivoId(tipoActivo);
        activoInformatico.setEmpleadoId(empleado);
        activoInformatico.setHardwareId(hardware);
        activoInformatico.setSoftwareId(software);
        activoInformatico.setRedId(red);

        activoInformatico = activoInformaticoRepository.save(activoInformatico);

        return mapToDTO(activoInformatico);
    }

    @Override
    public void deleteActivoInformatico(Long idactivoInformatico) {
        activoInformaticoRepository.deleteById(idactivoInformatico);
    }

    @Override
    public ActivoInformaticoDTO getActivoInformaticoById(Long idactivoInformatico) {
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
        dto.setTipoActivoId(activoInformatico.getTipoActivoId().getId());
        dto.setEmpleadoId(activoInformatico.getEmpleadoId().getId());
        dto.setHardwareId(activoInformatico.getHardwareId().getId());
        dto.setSoftwareId(activoInformatico.getSoftwareId().getId());
        dto.setRedId(activoInformatico.getRedId().getId());
        return dto;
    }

}
