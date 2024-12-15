package pe.sia.persistence.entity.problema;

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

import java.time.Instant;

/*
 * @author Villalta Carnero Anderson
 * @version 1.0
 ** Entidad que representa la auditoría de cambios en las problema.
 ** Esta clase tiene relaciones con las entidades DetalleProblema y Personal.
 */
@Entity
@Table(name = "auditoria")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Auditoria {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fecha_cambio", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Instant fechaCambio;

    @NotNull
    @Column(name = "cambio_realizado")
    private String cambioRealizado;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "mantenimiento_id", referencedColumnName = "id")
    private Mantenimiento mantenimiento;

}
