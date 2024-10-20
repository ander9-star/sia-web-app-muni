package pe.sia.persistence.entity.incidencias;

import java.time.LocalDate;

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

/*
 * @author Villalta Carnero Anderson
 * @version 1.0
 ** Esta entidad representa un mantenimiento que puede tener un activo de acuerdo a la incidencia
 */

@Entity
@Table(name = "mantenimiento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mantenimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDate fechaProgramada;

    private LocalDate fechaRealizada;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "tipo_mantenimiento_id", referencedColumnName = "id")
    private TipoMantenimiento tipoMantenimiento;

    private String notas;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "fallo_incidencia_id", referencedColumnName = "id")
    private FalloIncidencia falloIncidencia;
    
}
