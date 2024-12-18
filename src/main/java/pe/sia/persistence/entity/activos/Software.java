package pe.sia.persistence.entity.activos;

import java.time.LocalDate;
import jakarta.persistence.Column;
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
 * @author villalta carnero 
 * @version 1.0
 ** Entidad que representa el software en el sistema.Esta clase tiene una relación muchos 
 ** a uno con la entidad Proveedor,
 */

@Entity
@Table(name = "software")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Software {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String nombre;

    @Null
    private String version;

    @Null
    @Column(name = "fecha_instalacion")
    private LocalDate fechaInstalacion;

    @Column(name = "fecha_vencimiento_licencia")
    private LocalDate fechaVencimientoLicencia;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "proveedor_id", referencedColumnName = "id")
    private Proveedor proveedor;

}