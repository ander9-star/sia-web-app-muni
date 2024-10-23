package pe.sia.service.interfaces;

import pe.sia.presentation.dto.activosDTO.ActivoInformaticoDTO;
import java.util.List;

public interface ActivoInformaticoService {
    ActivoInformaticoDTO createActivoInformatico(ActivoInformaticoDTO activoInformaticoDTO);
    ActivoInformaticoDTO updateActivoInformatico(Long idactivoInformaticoDTO, ActivoInformaticoDTO activoInformaticoDTO);
    void deleteActivoInformatico(Long idactivoInformaticoDTO);
    ActivoInformaticoDTO getActivoInformaticoById(Long idactivoInformaticoDTO);
    List<ActivoInformaticoDTO> getAllActivoInformatico();
}
