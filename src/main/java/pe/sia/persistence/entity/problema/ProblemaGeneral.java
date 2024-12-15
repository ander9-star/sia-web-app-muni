package pe.sia.persistence.entity.problema;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.sia.persistence.entity.activos.ActivoMaestro;
import pe.sia.persistence.entity.actores.Usuario;

import java.time.Instant;

@Entity
@Table(name = "problema_general")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProblemaGeneral {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "codigo_problema_general", length = 12, insertable = false, unique = true, updatable = false)
    String codigoProblemaGeneral;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "activo_maestro_id", referencedColumnName = "id")
    private ActivoMaestro activoMaestro;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    @Column(name = "fecha_ocurrencia", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Instant fechaOcurrencia;

}
