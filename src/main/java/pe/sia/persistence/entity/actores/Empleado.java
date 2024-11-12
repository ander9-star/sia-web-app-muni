package pe.sia.persistence.entity.actores;

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
import pe.sia.persistence.entity.ubicaciones.OficinaSubgerencia;

@Entity
@Table(name = "empleado")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(unique = true, length = 12)
    private String codigo;

    @NotNull
    private String nombre;

    @NotNull
    private String apellidos;

    @NotNull
    private String cargo;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "oficina_subgerencia_id", referencedColumnName = "id")
    private OficinaSubgerencia oficinaSubgerencia;
}
