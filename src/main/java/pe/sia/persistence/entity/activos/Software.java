package pe.sia.persistence.entity.activos;

import java.time.LocalDate;

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
 * @author villalta carnero 
 * @version 1.0
 ** Entidad que representa el software en el sistema.Esta clase tiene una relación muchos 
 ** a uno con la entidad Proveedor,
 */

@Entity
@Table(name = "software")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Software {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nombre;

    @NotNull
    private String version;

    @NotNull
    private LocalDate fechaInstalacion;

    private LocalDate fechaVencimiento;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "proveedor_id", referencedColumnName = "id")
    private Proveedor proveedor;
}
