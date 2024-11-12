package pe.sia.presentation.dto.actoresDTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pe.sia.persistence.entity.actores.Usuario;
import pe.sia.util.EstadoPeticion;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsuarioDTO extends EstadoPeticion {

    private String tokenJWT;
    private String refreshTokenJWT;
    private String expiracionTokenTime;
    private String dateTokenCreation;

    private Integer id;

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

    private String rol;
    private String oficina;
    private Usuario user;
    private List<Usuario> userList;
    private List<UsuarioDTO> listUsuarioDTO;
}