package pe.sia.service.interfaces;

import java.util.List;
import pe.sia.presentation.dto.activosDTO.HardwareDTO;

public interface HardwareService {

    HardwareDTO createHardware(HardwareDTO hardwareDTO);
    HardwareDTO updateHardware(Integer idHardware, HardwareDTO hardwareDTO);
    void deleteHardware(Integer idHardware);
    HardwareDTO getHardwareById(Integer idHardware);
    List<HardwareDTO> getAllHardware();
    HardwareDTO getFIPorHardware();

}
