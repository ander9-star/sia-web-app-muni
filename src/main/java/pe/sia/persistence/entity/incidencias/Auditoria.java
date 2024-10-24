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
import pe.sia.persistence.entity.actores.Personal;
import java.time.OffsetDateTime;

/*
 * @author Villalta Carnero Anderson
 * @version 1.0
 ** Entidad que representa la auditoría de cambios en las incidencias.
 ** Esta clase tiene relaciones con las entidades FalloIncidencia y Personal.
 */
@Entity
@Table(name = "auditoria")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Auditoria {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "fallo_incidencia_id", referencedColumnName = "id")
    private FalloIncidencia falloIncidencia;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "personal_id", referencedColumnName = "id")
    private Personal personal;

    @Column(name = "fecha_cambio", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime fechaCambio;

    @NotNull
    private String cambioRealizado;

}
