package pe.sia.persistence.entity.incidencias;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @author Villalta Carnero Anderson
 * @version 1.0
 ** Esta entidad representa el tipo de mantenimiento en el sistema de incidencias.
 */

@Entity
@Table(name = "tipo_mantenimiento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoMantenimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nombre;

    private String descripcion;
    
}
