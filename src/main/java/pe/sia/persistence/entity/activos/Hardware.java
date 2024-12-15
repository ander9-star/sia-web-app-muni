package pe.sia.persistence.entity.activos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * @author Villalta Carnero Anderson
 * @version 1.0
 ** Esta entidad representa el hardware en el sistema. Tiene una relación muchos a uno con la entidad Proveedor.
 ** Se utiliza para almacenar información sobre equipos físicos como computadoras, laptops, etc.
 */

@Entity
@Table(name = "hardware")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Hardware {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Null
    private String nombre;

    @Null
    private String marca;
    
    @Null
    private String modelo;

    @NotNull
    @ManyToOne
    @JoinColumn(name="proveedor_id", referencedColumnName = "id")
    private Proveedor proveedor;
}
