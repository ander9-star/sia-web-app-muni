package pe.sia.service.implementation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.sia.persistence.repository.ubicaciones.OficinaSubgerenciaRepository;
import pe.sia.presentation.dto.ubicacionDTO.OficinaSubgerenciaDTO;
import pe.sia.service.interfaces.OficinaSubgerenciaService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class OficinaSubgerenciaServiceImpl implements OficinaSubgerenciaService {

    private final OficinaSubgerenciaRepository oficinaSubgerenciaRepository;

    public OficinaSubgerenciaServiceImpl(OficinaSubgerenciaRepository oficinaSubgerenciaRepository) {
        this.oficinaSubgerenciaRepository = oficinaSubgerenciaRepository;
    }

    @Override
    public OficinaSubgerenciaDTO getDataForProfile() {
        OficinaSubgerenciaDTO requestDTO = new OficinaSubgerenciaDTO();
        try {
            List<Object[]> resultTable = oficinaSubgerenciaRepository.findAllDataForProfile();
            List<OficinaSubgerenciaDTO> oficinaSubgerenciaDTOList = new ArrayList<>();
            if(!resultTable.isEmpty()) {
                for(Object[] object : resultTable) {
                    OficinaSubgerenciaDTO oficinaSubgerenciaDTO = new OficinaSubgerenciaDTO();
                    oficinaSubgerenciaDTO.setId((Integer) object[0]);
                    oficinaSubgerenciaDTO.setNombreOficina((String) object[1]);
                    oficinaSubgerenciaDTOList.add(oficinaSubgerenciaDTO);
                }
                requestDTO.setListOficinas(oficinaSubgerenciaDTOList);
                requestDTO.setStatusCode(200);
                requestDTO.setMessage("Se ha obtenido el id y nombre de las oficinas");
            } else {
                requestDTO.setStatusCode(403);
                requestDTO.setMessage("No hay data que cargar");
            }
            return requestDTO;
        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setMessage("Error al obtener la data: " + e.getMessage());
            return requestDTO;
        }
    }

    @Override
    public OficinaSubgerenciaDTO getFIPorOficina() {
        OficinaSubgerenciaDTO requestDTO = new OficinaSubgerenciaDTO();
        try {
            List<Object[]> resultTable = oficinaSubgerenciaRepository.getFIPorOficina();
            List<OficinaSubgerenciaDTO> oficinaSubgerenciaDTOList = new ArrayList<>();
            if(!resultTable.isEmpty()) {
                for(Object[] object : resultTable) {
                    OficinaSubgerenciaDTO oficinaSubgerenciaDTO = new OficinaSubgerenciaDTO();
                    oficinaSubgerenciaDTO.setId((Integer) object[0]);
                    oficinaSubgerenciaDTO.setNombreOficina((String) object[1]);
                    oficinaSubgerenciaDTO.setOficinaGeren((String) object[2]);
                    oficinaSubgerenciaDTO.setOficinaMayor((String) object[3]);
                    oficinaSubgerenciaDTO.setUbicacion((String) object[4]);
                    oficinaSubgerenciaDTO.setCantidadIncidencias((Integer) object[5]);
                    oficinaSubgerenciaDTOList.add(oficinaSubgerenciaDTO);
                }
                requestDTO.setListOficinas(oficinaSubgerenciaDTOList);
                requestDTO.setStatusCode(200);
                requestDTO.setMessage("Se ha obtenido la data completa de las oficinas");
            } else {
                requestDTO.setStatusCode(403);
                requestDTO.setMessage("No hay data que cargar");
            }
            return requestDTO;
        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setMessage("Error al obtener la data: " + e.getMessage());
            return requestDTO;
        }
    }


}
