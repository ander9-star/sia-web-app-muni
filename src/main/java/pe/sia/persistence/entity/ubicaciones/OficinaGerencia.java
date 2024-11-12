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
@Table(name = "oficina_gerencia")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OficinaGerencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String nombre;
     
    @NotNull
    @ManyToOne
    @JoinColumn(name = "oficina_mayor_id", referencedColumnName = "id")
    private OficinaMayor oficinaMayor;

}