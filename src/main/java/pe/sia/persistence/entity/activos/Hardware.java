package pe.sia.persistence.entity.activos;

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
 ** Esta entidad representa el hardware en el sistema. Tiene una relación muchos a uno con la entidad Proveedor.
 ** Se utiliza para almacenar información sobre equipos físicos como computadoras, laptops, etc.
 */

@Entity
@Table(name = "hardware")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hardware {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nombre;

    @NotNull
    private String marca;
    
    @NotNull
    private String modelo;

    @NotNull
    @ManyToOne
    @JoinColumn(name="proveedor_id", referencedColumnName = "id")
    private Proveedor proveedor;

    @NotNull
    private Long ordenCompra;
    
}
