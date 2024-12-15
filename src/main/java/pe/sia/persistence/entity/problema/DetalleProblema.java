package pe.sia.persistence.entity.problema;

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

import java.time.Instant;

/*
 * @author Villalta Carnero Anderson 
 * @version 1.0
 ** Esta entidad representa un el problema reportada en el sistema. Está relacionada con otras entidades
 ** como Ubicación, Activo Informático, Usuario, Personal, Categoría y Prioridad, lo que permite una mejor gestión y seguimiento de los fallos.
 */

@Entity
@Table(name = "detalle_problema")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DetalleProblema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "codigo_problema", unique = true, length = 5)
    private String codigoProblema;

    @NotNull
    private String descripcion;

    @Column(name = "fecha_registro", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Instant fechaRegistro;
    
    @NotNull
    @Column(name = "medio_reporte")
    private String medioReporte;

    private String solucion;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    private Categoria categoria;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "prioridad_id", referencedColumnName = "id")
    private Prioridad prioridad;

    private boolean solucionado;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "problema_general_id", referencedColumnName = "id")
    private ProblemaGeneral problemaGeneral;

}
