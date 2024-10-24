package pe.sia.presentation.dto.actoresDTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import pe.sia.persistence.entity.actores.Usuario;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsuarioDTO {

    private int statusCode;
    private String error;
    private String message;
    private String tokenJWT;
    private String refreshTokenJWT;
    private String expiracionTokenTime;
    private String dateTokenCreation;

    private Integer id;

    @NotNull
    private String nombre;

    @NotNull
    private String userName;
    
    @NotNull
    private String password;
    
    @NotNull
    private String correo;
    
    @NotNull
    private Integer ubicacionId;
    
    @NotNull
    private Integer rolId;
    
    private Usuario user;
    private List<Usuario> userList;
}