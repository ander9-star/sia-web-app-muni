package pe.sia.service.interfaces;

import java.util.List;

import pe.sia.presentation.dto.activosDTO.ProveedorDTO;

public interface ProveedorService {

    ProveedorDTO createProveedor(ProveedorDTO proveedorDTO);
    ProveedorDTO updateProveedor(Long idProveedor, ProveedorDTO proveedorDTO);
    void deleteProveedor(Long idProveedor);
    ProveedorDTO getProveedorById(Long idProveedor);
    List<ProveedorDTO> getAllProveedores();

}
