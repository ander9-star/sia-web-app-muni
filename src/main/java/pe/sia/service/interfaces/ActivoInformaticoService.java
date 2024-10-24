package pe.sia.service.interfaces;

import pe.sia.presentation.dto.activosDTO.ActivoInformaticoDTO;
import java.util.List;

public interface ActivoInformaticoService {
    ActivoInformaticoDTO createActivoInformatico(ActivoInformaticoDTO activoInformaticoDTO);
    ActivoInformaticoDTO updateActivoInformatico(Integer idactivoInformaticoDTO, ActivoInformaticoDTO activoInformaticoDTO);
    void deleteActivoInformatico(Integer idactivoInformaticoDTO);
    ActivoInformaticoDTO getActivoInformaticoById(Integer idactivoInformaticoDTO);
    List<ActivoInformaticoDTO> getAllActivoInformatico();
}
