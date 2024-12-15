package pe.sia.presentation.dto.actoresDTO;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import pe.sia.persistence.entity.actores.Usuario;
import pe.sia.util.EstadoPeticion;

@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO extends EstadoPeticion {
    private String tokenJWT;
    private String refreshTokenJWT;
    private String expiracionTokenTime;
    private String dateTokenCreation;
    private Integer idUsuario;

    @NotNull
    private String nombre;
    @NotNull
    private String apellidos;
    @NotNull
    private String userName;
    @NotNull
    private String password;
    @NotNull
    private String correo;
    @NotNull
    private Integer rolId;
    @NotNull
    private Integer oficinaSubgerenciaId;
    @NotNull
    private Boolean estado;
    private String rol;
    private String oficina;
    private Usuario user;
    private Integer cantidadProblemasRegistrados;
    private Integer cantidadDetalleProblemas;
    private UsuarioDTO usuarioDTO;
    private List<Usuario> userList;
    private List<UsuarioDTO> listUsuarioDTO;
}