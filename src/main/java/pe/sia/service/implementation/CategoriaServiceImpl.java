package pe.sia.service.implementation;

import org.springframework.stereotype.Service;
import pe.sia.persistence.entity.problema.Categoria;
import pe.sia.persistence.repository.problemaRepository.CategoriaRepository;
import pe.sia.presentation.dto.problemaDTO.CategoriaDTO;
import pe.sia.service.interfaces.CategoriaService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public CategoriaDTO insertCategoria(Categoria categoria) {
        CategoriaDTO requestDTO = new CategoriaDTO();
        try {
            Categoria categoriaNew = new Categoria();
            categoriaNew.setNombre(categoria.getNombre());
            Categoria newCategoria = categoriaRepository.save(categoriaNew);
            if(newCategoria.getId() > 0) {
                requestDTO.setStatusCode(201);
                requestDTO.setMessage("Categoria creada con éxito");
                requestDTO.setCategoria(newCategoria);
            }

        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setError("Ha ocurrido un error inesperado al crear el usuario: " + e.getMessage());
        }
        return requestDTO;
    }

    @Override
    public CategoriaDTO updateCategoria(Integer idCategoria, Categoria categoria) {
        CategoriaDTO requestDTO = new CategoriaDTO();
        try {
            Optional<Categoria> optionalCategoria = categoriaRepository.findById(idCategoria);
            optionalCategoria.ifPresentOrElse(categoriaUpdate -> {
                categoriaUpdate.setNombre(categoria.getNombre());
                categoriaRepository.save(categoriaUpdate);
                requestDTO.setStatusCode(200);
                requestDTO.setMessage("Categoria actualizada con éxito");
                requestDTO.setCategoria(categoriaUpdate);
            }, () -> {
                requestDTO.setStatusCode(404);
                requestDTO.setError("Categoria no encontrada");
            });
        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setError("Ha ocurrido un error inesperado al actualizar la categoria: " + e.getMessage());
        }
        return requestDTO;
    }

    @Override
    public CategoriaDTO deleteCategoria(Integer idCategoria) {
        CategoriaDTO requestDTO = new CategoriaDTO();
        try {
            Optional<Categoria> optionalCategoria = categoriaRepository.findById(idCategoria);
            optionalCategoria.ifPresentOrElse(categoriaDelete -> {
                categoriaRepository.delete(categoriaDelete);
                requestDTO.setStatusCode(200);
                requestDTO.setMessage("Categoria eliminada con éxito");
            }, () -> {
                requestDTO.setStatusCode(404);
                requestDTO.setError("Categoria no encontrada");
            });
        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setError("Ha ocurrido un error inesperado al eliminar la categoria: " + e.getMessage());
        }
        return requestDTO;
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
