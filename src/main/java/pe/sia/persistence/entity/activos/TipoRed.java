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

/*
 * @author Villalta Carnero Anderson
 * @version 1.0
 ** Esta entidad representa el tipo de red en el sistema. Se utiliza para clasificar los diferentes tipos de redes. 
 ** Tiene una relación uno a muchos con la entidad Red.
 */

@Entity
@Table(name = "tipo_red")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoRed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nombre;
    
}
