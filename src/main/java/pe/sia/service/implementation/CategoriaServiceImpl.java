package pe.sia.service.implementation;

import org.springframework.stereotype.Service;
import pe.sia.persistence.repository.incidenciasRepository.CategoriaRepository;
import pe.sia.presentation.dto.incienciasDTO.CategoriaDTO;
import pe.sia.service.interfaces.CategoriaService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public CategoriaDTO listarAllCategoria() {
        CategoriaDTO response = new CategoriaDTO();
        try {
            List<Object[]> resultTable = categoriaRepository.listAllCategoria();
            List<CategoriaDTO> listCategoriaDTO = new ArrayList<>();

            if (!resultTable.isEmpty()) {
                for (Object[] object : resultTable) {
                    CategoriaDTO objeto = new CategoriaDTO();
                    objeto.setId((Integer) object[0]);
                    objeto.setNombre((String) object[1]);
                    listCategoriaDTO.add(objeto);
                }
                response.setStatusCode(200);
                response.setMessage("Se ha listado correctamente las categorias");
                response.setListCategoria(listCategoriaDTO);
            }
            return response;

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setError("Ha sucedido un error al listar las categorias: " + e.getMessage());
            return response;
        }
    }
}
