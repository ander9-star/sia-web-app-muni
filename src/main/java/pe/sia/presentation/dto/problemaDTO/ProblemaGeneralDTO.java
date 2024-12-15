package pe.sia.presentation.dto.problemaDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import pe.sia.util.EstadoPeticion;

@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProblemaGeneral extends EstadoPeticion {

    private Integer id;
}
