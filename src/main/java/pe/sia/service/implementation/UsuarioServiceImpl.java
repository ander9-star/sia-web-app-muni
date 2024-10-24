package pe.sia.service.implementation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import pe.sia.persistence.entity.actores.Rol;
import pe.sia.persistence.entity.actores.Usuario;
import pe.sia.persistence.entity.ubicaciones.Area;
import pe.sia.persistence.repository.actoresRepository.RolRepository;
import pe.sia.persistence.repository.actoresRepository.UsuarioRepository;
import pe.sia.persistence.repository.ubicaciones.AreaRepository;
import pe.sia.presentation.dto.actoresDTO.UsuarioDTO;
import pe.sia.service.interfaces.UsuarioService;
import pe.sia.util.JWTokenUtils;

@Slf4j
@Service
public class UsuarioServiceImpl implements UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private JWTokenUtils jwTokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UsuarioDTO createUsuario(UsuarioDTO usuarioCreateDTO) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        try {
            Usuario usuario = new Usuario();
            usuario.setNombre(usuarioCreateDTO.getNombre());
            usuario.setUserName(usuarioCreateDTO.getUserName());
            usuario.setPassword(passwordEncoder.encode(usuarioCreateDTO.getPassword()));
            usuario.setCorreo(usuarioCreateDTO.getCorreo());
            Rol rol = rolRepository.findById(3).orElseThrow();
            usuario.setRol(rol);
            Area area = areaRepository.findById(1).orElseThrow();
            usuario.setArea(area);
            usuario = usuarioRepository.save(usuario);

            if(usuario.getId() > 0) {
                usuarioDTO.setUser((usuario));
                usuarioDTO.setStatusCode(201);
                usuarioDTO.setMessage("Usuario: " + usuario.getNombre() + " ha sido creado con éxito"); 
            }

        } catch (Exception e) {
            usuarioDTO.setStatusCode(500);
            usuarioDTO.setError("Ha ocurrido un error inesperado al crear el usuario: " + e.getMessage());
        }
        return usuarioDTO;
    }

    @Override
    public UsuarioDTO updateUsuario(Integer userId, Usuario updateUser) { 
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        try {
             Optional<Usuario> userOptional = usuarioRepository.findById(userId);
             if(userOptional.isPresent()) {
                Usuario usuarioUpdate = userOptional.get();
                usuarioUpdate.setNombre(updateUser.getNombre());
                usuarioUpdate.setUserName(updateUser.getUsername());
                usuarioUpdate.setCorreo(updateUser.getCorreo());
                usuarioUpdate.setArea(updateUser.getArea());
                usuarioUpdate.setRol(updateUser.getRol()); 
                if(updateUser.getPassword() != null && !updateUser.getPassword().isEmpty()) {
                    // codificar la password
                    usuarioUpdate.setPassword(passwordEncoder.encode(updateUser.getPassword()));
                }
                Usuario newUsuario = usuarioRepository.save(usuarioUpdate);
                usuarioDTO.setUser(newUsuario);
                usuarioDTO.setStatusCode(200);
                usuarioDTO.setMessage("Usuario: " + usuarioUpdate.getNombre() + " actualizado con éxito");
             } else {
                usuarioDTO.setStatusCode(404);
                usuarioDTO.setMessage("Usuario: " + updateUser.getNombre() + " no encontrado");
             }
        } catch (Exception e) {
             usuarioDTO.setStatusCode(500);
             usuarioDTO.setError("Ha ocurrido un error inesperado al actualizar el usuario: " + e.getMessage());
        }
        return usuarioDTO;
    }

    @Override
    public UsuarioDTO deleteUsuario(Integer idUsuario) {
       UsuarioDTO usuarioDTO = new UsuarioDTO();

       try {
            Optional<Usuario> userOptional = usuarioRepository.findById(idUsuario);
            if(userOptional.isPresent()) {
                usuarioRepository.deleteById(idUsuario);
                usuarioDTO.setStatusCode(204);
                usuarioDTO.setMessage("Usuario eliminado con éxito");
            } else {
                usuarioDTO.setStatusCode(404);
                usuarioDTO.setMessage("Usuario: " + userOptional.get().getNombre() + " no encontrado");
            }
       } catch (Exception e) {
            usuarioDTO.setStatusCode(500);
            usuarioDTO.setError("Ha ocurrido un error inesperado al eliminar el usuario: " + e.getMessage());
       }
       return usuarioDTO;
    }

    @Override
    public UsuarioDTO getUsuarioById(Integer idUsuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();

        try {
            Usuario usuarioById = usuarioRepository.findById(idUsuario).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            usuarioDTO.setUser(usuarioById);
            usuarioDTO.setStatusCode(200);
            usuarioDTO.setMessage("Usuario encontrado con el id: " + usuarioById + " exitosamente");

        } catch (Exception e) {
            usuarioDTO.setStatusCode(500);
            usuarioDTO.setError("Ha ocurrido un error inesperado: " + e.getMessage());
        }
        return usuarioDTO;
    }

    @Override
    public UsuarioDTO getAllUsuarios() {
        UsuarioDTO requestDTO = new UsuarioDTO();

        try {
            List<Usuario> userList = usuarioRepository.findAll();
            if(!userList.isEmpty()) {
                requestDTO.setUserList(userList);
                requestDTO.setStatusCode(200);
                requestDTO.setMessage("Listado de los usuarios con éxito");
            } else {
                requestDTO.setStatusCode(204);
                requestDTO.setError("No hay usuarios que devolver");
            }
            return requestDTO;
        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setError("Ha ocurrido un error inesperado: " + e.getMessage());
            return requestDTO;
        }
    } 

    @Override
    public UsuarioDTO login(UsuarioDTO usuarioLoginDTO) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();

        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(usuarioLoginDTO.getUserName(),
                                  usuarioLoginDTO.getPassword()));
            var user = usuarioRepository.findByUserName(usuarioLoginDTO.getUserName()).orElseThrow();
            var jwt = jwTokenService.generateTokenJWT(user);
            var refreshToken = jwTokenService.generateRefreshTokenJWT(new HashMap<>(), user);
            usuarioDTO.setStatusCode(200);
            usuarioDTO.setTokenJWT(jwt); 
            usuarioDTO.setRolId(user.getRol().getId());
            usuarioDTO.setRefreshTokenJWT(refreshToken);
            usuarioDTO.setExpiracionTokenTime("24Hrs");
            usuarioDTO.setMessage("Usuario logeado con éxito, Rol " + user.getRol());
            usuarioDTO.setDateTokenCreation(this.getFormatoFechaActual());
            log.info("\n Token generado: " + usuarioDTO.getTokenJWT());

        } catch (Exception e) {
            usuarioDTO.setStatusCode(500);
            usuarioDTO.setMessage("Ha ocurrido un problema al logearse: " + e.getMessage());
        }
        
        return usuarioDTO;
    }

    @Override
    public UsuarioDTO refreshToken(UsuarioDTO usuarioRefreshDTO) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        
        try {
            String userName = jwTokenService.extraerUsername(usuarioRefreshDTO.getTokenJWT());
            Usuario usuario = usuarioRepository.findByUserName(userName).orElseThrow();
            
            if(jwTokenService.isTokenValid(usuarioRefreshDTO.getTokenJWT(), usuario)) {
                var jwt = jwTokenService.generateTokenJWT(usuario);
                usuarioDTO.setStatusCode(200);
                usuarioDTO.setTokenJWT(jwt);
                usuarioDTO.setRefreshTokenJWT(usuarioRefreshDTO.getTokenJWT());
                usuarioDTO.setExpiracionTokenTime("24Hrs");
                usuarioDTO.setMessage("Token refrescado con éxito");
                usuarioDTO.setDateTokenCreation(this.getFormatoFechaActual());
            }
            usuarioDTO.setStatusCode(200);
            return usuarioDTO;
        } catch (Exception e) {
            usuarioDTO.setStatusCode(500);
            usuarioDTO.setMessage("Ha ocurrido un problema al generar el token: " + e.getMessage());
            return usuarioDTO;
        }
    }

    @Override
    public UsuarioDTO getMyProfile(String userName) {
        UsuarioDTO requestDTO = new UsuarioDTO();

        try {
            Optional<Usuario> userOptionalName = usuarioRepository.findByUserName(userName);
            if(userOptionalName.isPresent()) {
                requestDTO.setUser(userOptionalName.get());
                requestDTO.setStatusCode(200);
                requestDTO.setMessage("Successful");
            } else {
                requestDTO.setStatusCode(404);
                requestDTO.setMessage("Usuario no encontrado");
            }
        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setError("Ha ocurrido un error al actualizar el usuario: "+ e.getMessage());
        }

        return requestDTO;
    }

    private String getFormatoFechaActual() {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTimeFormatter.format(dateTime);
    }
}
