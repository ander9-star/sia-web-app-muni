package pe.sia.persistence.entity.activos;

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
import pe.sia.persistence.entity.actores.Empleado;

@Entity
@Table(name = "activo_maestro")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ActivoMaestro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "codigo_bien", unique = true, length = 12)
    private String codigoBien;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "activo_informatico_id", referencedColumnName = "id")
    private ActivoInformatico activoInformatico;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "empleado_id", referencedColumnName = "id")
    private Empleado empleado;
    
}
