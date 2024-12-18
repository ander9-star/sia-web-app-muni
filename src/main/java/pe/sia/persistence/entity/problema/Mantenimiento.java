package pe.sia.persistence.entity.problema;

import java.time.Instant;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.sia.persistence.entity.actores.Personal;

/*
 * @author Villalta Carnero Anderson
 * @version 1.0
 ** Esta entidad representa un mantenimiento que puede tener un activo de acuerdo a la incidencia
 */

@Entity
@Table(name = "mantenimiento")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Mantenimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "detalle_problema_id", referencedColumnName = "id")
    private DetalleProblema detalleProblema;

    @NotNull
    @Column(name = "fecha_programada", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Instant fechaProgramada;

    @Column(name = "fecha_realizada", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Instant fechaRealizada;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "tipo_mantenimiento_id", referencedColumnName = "id")
    private TipoMantenimiento tipoMantenimiento;

    private String notas;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "personal_id", referencedColumnName = "id")
    private Personal personal;
    
}
