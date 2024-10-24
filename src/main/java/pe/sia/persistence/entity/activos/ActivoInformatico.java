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
import pe.sia.persistence.entity.actores.Empleado;

/*
 * @author Villalta Carnero Anderson
 * @version 1.0
 ** Esta entidad representa un activo informático en el sistema, incluyendo sus relaciones 
 ** con tipo de activo, empleado, hardware, software y red.
 */

@Entity
@Table(name = "activo_informatico")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivoInformatico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "tipo_activo_id", referencedColumnName = "id")
    private TipoActivo tipoActivo;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "empleado_id", referencedColumnName = "id")
    private Empleado empleado;

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
    @JoinColumn(name = "red_id", referencedColumnName = "id")
    private Red red;
}
