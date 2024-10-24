package pe.sia.service.interfaces;

import java.util.List;

import pe.sia.presentation.dto.activosDTO.ProveedorDTO;

public interface ProveedorService {

    ProveedorDTO createProveedor(ProveedorDTO proveedorDTO);
    ProveedorDTO updateProveedor(Integer idProveedor, ProveedorDTO proveedorDTO);
    void deleteProveedor(Integer idProveedor);
    ProveedorDTO getProveedorById(Integer idProveedor);
    List<ProveedorDTO> getAllProveedores();

}
