package pe.sia.persistence.entity.ubicaciones;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.sia.persistence.entity.actores.Area;

/*
 * @author Villalta Carnero Anderson
 * @version 1.0
 ** Esta entidad representa la ubicacion del activo informatico cuando se genera la incidencia
 ** tiene relacion con las tablas de Oficina, Area y Piso
 */

@Entity
@Table(name = "ubicacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ubicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nombre;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "oficina_id", referencedColumnName = "id")
    private Oficina oficina;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "area_id", referencedColumnName = "id")
    private Area area;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "piso_id", referencedColumnName = "id")
    private Piso piso;
    
}
