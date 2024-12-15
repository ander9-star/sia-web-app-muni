package pe.sia.service.interfaces;

import pe.sia.persistence.entity.problema.Categoria;
import pe.sia.presentation.dto.problemaDTO.CategoriaDTO;

public interface CategoriaService {

    // para crear una categoria
    CategoriaDTO insertCategoria(Categoria categoria);

    // para actualizar una categoria
    CategoriaDTO updateCategoria(Integer idCategoria, Categoria categoria);

    // para eliminar una categoria
    CategoriaDTO deleteCategoria(Integer idCategoria);
    CategoriaDTO listarAllCategoria();
}
