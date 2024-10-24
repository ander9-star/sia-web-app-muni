package pe.sia.persistence.entity.activos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Villalta Carnero Anderson
 * @version 1.0
 * Esta entidad representa el tipo de activo en el sistema. Se utiliza para clasificar los 
 * diferentes tipos de activos informáticos.
 */

@Entity
@Table(name = "tipo_activo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoActivo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String nombre;
}
