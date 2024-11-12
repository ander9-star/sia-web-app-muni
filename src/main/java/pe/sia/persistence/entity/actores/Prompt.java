package pe.sia.persistence.entity.actores;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * @author Villalta Carnero Anderson
 * @version 1.0
 ** Esta entidad representa un prompt en el sistema. Tiene una relación muchos a uno con la entidad Usuario.
 ** Se utiliza para almacenar información sobre los prompts generados por los usuarios hacia la AI.
 */

@Entity
@Table(name = "prompt")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Prompt {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @JoinColumn(name = "texto")
    private String prompt;

    @NotNull
    private LocalDate fecha;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;
}
