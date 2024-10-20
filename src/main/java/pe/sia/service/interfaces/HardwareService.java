package pe.sia.service.interfaces;

import java.util.List;

import pe.sia.presentation.dto.activosDTO.HardwareDTO;

public interface HardwareService {

    HardwareDTO createHardware(HardwareDTO hardwareDTO);
    HardwareDTO updateHardware(Long idHardware, HardwareDTO hardwareDTO);
    void deleteHardware(Long idHardware);
    HardwareDTO getHardwareById(Long idHardware);
    List<HardwareDTO> getAllHardware();

}
