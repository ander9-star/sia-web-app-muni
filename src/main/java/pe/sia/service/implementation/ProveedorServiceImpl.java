package pe.sia.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import pe.sia.persistence.entity.activos.Proveedor;
import pe.sia.persistence.repository.activosRepository.ProveedorRepository;
import pe.sia.presentation.dto.activosDTO.ProveedorDTO;
import pe.sia.service.interfaces.ProveedorService;

/**
 * @author villalta carnero anderson
 * @version 1.0
 */

@Service
public class ProveedorServiceImpl implements ProveedorService {

    private final ProveedorRepository proveedorRepository;

    public ProveedorServiceImpl(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    // Paso 1: Método para crear un nuevo proveedor a traves de de un DTO
    @Override
    public ProveedorDTO createProveedor(ProveedorDTO proveedorDTO) {

        // Paso 1: crear el objeto proveedor
        Proveedor proveedor = new Proveedor();

        // Paso 2: obtener la informacion (valor) para cada campo
        proveedor.setNombre(proveedorDTO.getNombre());
        proveedor.setContacto(proveedorDTO.getContacto());
        proveedor.setTelefono(proveedorDTO.getTelefono());

        // Paso 3: crear (guardar) el nuevo objeto desde el repositorio de Proveedor
        proveedor = proveedorRepository.save(proveedor);

        // Paso 4: devolver el nuevo objeto creado
        return mapToDTO(proveedor);
    }

    // Paso 2: Método para actualizar un proveedor si exsite a tráves de un DTO
    @Override
    public ProveedorDTO updateProveedor(Integer idProveedor, ProveedorDTO proveedorDTO) {

        // Paso 1: hacer la validacion si existe el Proveedor en la base de datos, si existe, paso 2
        Proveedor proveedor = proveedorRepository
                                .findById(idProveedor)
                                .orElseThrow(() -> new RuntimeException("El proveedor " + proveedorDTO.getNombre() + " no existe"));

        // Paso 2: obtener la informacion (valor) para cada campo
        proveedor.setNombre(proveedorDTO.getNombre());
        proveedor.setContacto(proveedorDTO.getContacto());
        proveedor.setTelefono(proveedorDTO.getTelefono());

        // crear (guardar) el nuevo objeto desde el repositorio de Proveedor
        proveedor = proveedorRepository.save(proveedor);

        // devolver el nuevo objeto creado
        return mapToDTO(proveedor);
    }

    // Paso 3: Método para eliminar un proveedor
    @Override
    public void deleteProveedor(Integer idProveedor) {
        proveedorRepository.deleteById(idProveedor);
    }

    // Paso 4: Método para obtener un proveedor a tráves de un Id
    @Override
    public ProveedorDTO getProveedorById(Integer idProveedor) {
        Proveedor proveedor = proveedorRepository
                                    .findById(idProveedor)
                                    .orElseThrow(() -> new RuntimeException("El proveedor no existe"));

        return mapToDTO(proveedor);
    }

    // Paso 5: Método para obtener todos los proveedores que hay en la base de datos
    @Override
    public List<ProveedorDTO> getAllProveedores() {
        return proveedorRepository.findAll()
                    .stream()
                    .map(this::mapToDTO)
                    .collect(Collectors.toList());
    }

    // Método auxiliar para mapear un Proveedor a un DTO
    private ProveedorDTO mapToDTO(Proveedor proveedor) {
        ProveedorDTO dto = new ProveedorDTO();
        dto.setId(proveedor.getId());
        dto.setNombre(proveedor.getNombre());
        dto.setContacto(proveedor.getContacto());
        dto.setTelefono(proveedor.getNombre());
        return dto;
    }

}