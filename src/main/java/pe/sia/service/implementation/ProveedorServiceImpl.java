package pe.sia.service.implementation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    // Paso 1: Metodo para crear un nuevo proveedor a traves de de un DTO
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

    // Paso 2: Metodo para actualizar un proveedor si exsite a tráves de un DTO
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

    // Paso 3: Metodo para eliminar un proveedor
    @Override
    public void deleteProveedor(Integer idProveedor) {
        proveedorRepository.deleteById(idProveedor);
    }

    // Paso 4: Metodo para obtener un proveedor a tráves de un Id
    @Override
    public ProveedorDTO getProveedorById(Integer idProveedor) {
        Proveedor proveedor = proveedorRepository
                                    .findById(idProveedor)
                                    .orElseThrow(() -> new RuntimeException("El proveedor no existe"));

        return mapToDTO(proveedor);
    }

    // Paso 5: Metodo para obtener todos los proveedores que hay en la base de datos
    @Override
    public List<ProveedorDTO> getAllProveedores() {
        return proveedorRepository.findAll()
                    .stream()
                    .map(this::mapToDTO)
                    .toList();
    }

    // Metodo auxiliar para mapear un Proveedor a un DTO
    private ProveedorDTO mapToDTO(Proveedor proveedor) {
        ProveedorDTO dto = new ProveedorDTO();
        dto.setId(proveedor.getId());
        dto.setNombre(proveedor.getNombre());
        dto.setContacto(proveedor.getContacto());
        dto.setTelefono(proveedor.getNombre());
        return dto;
    }

    //
    @Override
    public ProveedorDTO getDataProveedor() {
        ProveedorDTO requestDTO = new ProveedorDTO();
        try {
            List<Object[]> resultTable = proveedorRepository.getFIPorProveedor();
            List<ProveedorDTO> proveedorDTOList = new ArrayList<>();
            if(!resultTable.isEmpty()) {
                for(Object[] row : resultTable) {
                    ProveedorDTO proveedorDTO = new ProveedorDTO();
                    proveedorDTO.setStatusCode(200);
                    proveedorDTO.setNombre((String) row[0]);
                    proveedorDTO.setContacto((String) row[1]);
                    proveedorDTO.setTelefono((String) row[2]);
                    proveedorDTO.setCantidadIncidencias((Integer) row[3]);
                    proveedorDTO.setPorcentajeIncidencia((BigDecimal) row[4]);
                    proveedorDTOList.add(proveedorDTO);
                }
                requestDTO.setStatusCode(200);
                requestDTO.setMessage("Se ha extraido con éxito la data de proveedor");
                requestDTO.setProveedorDTOList(proveedorDTOList);
            } else {
                requestDTO.setStatusCode(404);
                requestDTO.setMessage("No hay data que extraer");
            }
            return requestDTO;
        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setMessage("Ha sucedido un error al intenttar extraer la data: " + e.getMessage());
            return requestDTO;
        }
    }

}