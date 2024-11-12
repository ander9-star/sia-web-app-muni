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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "oficina_subgerencia")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OficinaSubgerencia {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String nombre;

    private Integer piso;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "oficina_gerencia_id", referencedColumnName = "id")
    private OficinaGerencia oficinaGerencia;

}
