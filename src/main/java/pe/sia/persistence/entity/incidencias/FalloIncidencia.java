package pe.sia.persistence.entity.incidencias;

import jakarta.persistence.Column;
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
import pe.sia.persistence.entity.activos.ActivoInformatico;
import pe.sia.persistence.entity.actores.Personal;
import pe.sia.persistence.entity.actores.Usuario;
import pe.sia.persistence.entity.ubicaciones.Area;
import java.time.OffsetDateTime;

/*
 * @author Villalta Carnero Anderson 
 * @version 1.0
 ** Esta entidad representa un fallo o incidencia reportada en el sistema. Está relacionada con otras entidades 
 ** como Ubicación, Activo Informático, Usuario, Personal, Categoría y Prioridad, lo que permite una mejor gestión y seguimiento de los fallos.
 */

@Entity
@Table(name = "fallo_incidencia")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FalloIncidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String descripcion;

    @Column(name = "fecha_ocurrencia", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime fechaOcurrencia;
    
    @NotNull
    @Column(name = "medio_reporte")
    private String medioReporte;

    private String solucion;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "activo_id", referencedColumnName = "id")
    private ActivoInformatico activoInformatico;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "personal_id", referencedColumnName = "id")
    private Personal personal;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    private Categoria categoria;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "prioridad_id", referencedColumnName = "id")
    private Prioridad prioridad;
    
    private boolean solucionado;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;
        
    @NotNull
    @ManyToOne
    @JoinColumn(name = "area_id", referencedColumnName = "id")
    private Area area;

}
