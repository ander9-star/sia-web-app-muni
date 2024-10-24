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
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @author villalta carnero anderson
 * @version 1.0
 ** Esta entidad representa una red en el sistema. Tiene relaciones muchos a uno con las entidades Proveedor y TipoRed.
 ** Se utiliza para almacenar información sobre las redes como servidores, router, switch, entre otros.
 */

@Entity
@Table(name = "red")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Red {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String modelo;
    
    @NotNull
    @Column(name = "direccion_ip")
    private String direccionIP;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "proveedor_id", referencedColumnName = "id")
    private Proveedor proveedor;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "tipo_red_id", referencedColumnName = "id") 
    private TipoRed tipoRed;

    @NotNull
    @Column(name = "orden_compra")
    private Long ordenCompra;
}
