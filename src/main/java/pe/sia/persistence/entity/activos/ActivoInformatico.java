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

/*
 * @author Villalta Carnero Anderson
 * @version 1.0
 ** Esta entidad representa un activo informático en el sistema, incluyendo sus relaciones 
 ** con tipo de activo, empleado, hardware, software y red.
 */

@Entity
@Table(name = "activo_informatico")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ActivoInformatico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "orden_compra", unique = true, length = 12)
    private String ordenCompra;
    
    @NotNull
    @Column(name = "tipo_activo")
    private String tipoActivo;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "hardware_id", referencedColumnName = "id")
    private Hardware hardware;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "software_id", referencedColumnName = "id")
    private Software software;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "infraestructura_red_id", referencedColumnName = "id")
    private InfraestructuraRed infraestructuraRed;
}
