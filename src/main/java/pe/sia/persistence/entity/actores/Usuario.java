package pe.sia.persistence.entity.actores;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

/*
 * @author Villalta Carnero Anderson
 * @version 1.0
 ** Esta entidad representa a un usuario en el sistema. Tiene relaciones muchos a uno con las entidades Rol y Area.
 ** Se utiliza para almacenar información sobre los usuarios que acceden al sistema.
 */

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nombre;

    @NotNull
    private String usuario;

    @NotNull
    private String password;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "rol_id", referencedColumnName = "id")
    private Rol rol;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "area_id", referencedColumnName = "id")
    private Area area;
    
}
