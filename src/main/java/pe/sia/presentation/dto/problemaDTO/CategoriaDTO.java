package pe.sia.presentation.dto.incienciasDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;
import pe.sia.persistence.entity.problema.Categoria;
import pe.sia.util.EstadoPeticion;

import java.util.List;


@EqualsAndHashCode(callSuper = false)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoriaDTO extends EstadoPeticion {
    private Integer id;
    private String nombre;
    private List<CategoriaDTO> listCategoria;
    private Categoria categoria;
}
