package pe.sia.service.implementation;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import pe.sia.persistence.entity.actores.Rol;
import pe.sia.persistence.entity.actores.Usuario;
import pe.sia.persistence.entity.ubicaciones.OficinaSubgerencia;
import pe.sia.persistence.repository.actoresRepository.RolRepository;
import pe.sia.persistence.repository.actoresRepository.UsuarioRepository;
import pe.sia.persistence.repository.ubicaciones.OficinaSubgerenciaRepository;
import pe.sia.presentation.dto.actoresDTO.UsuarioDTO;
import pe.sia.service.interfaces.UsuarioService;
import pe.sia.util.JWTokenUtils;
import pe.sia.util.UtilsApp;

@Slf4j
@Service
//@RequiredArgsConstructor(onConstructor = @__(@Lazy))  
public class UsuarioServiceImpl implements UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private OficinaSubgerenciaRepository oficinaSubgerenciaRepository;

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
            usuario.setApellidos(usuarioCreateDTO.getApellidos()); 
            usuario.setUserName(usuarioCreateDTO.getUserName());
            usuario.setPassword(passwordEncoder.encode(usuarioCreateDTO.getPassword()));
            usuario.setCorreo(usuarioCreateDTO.getCorreo());
            Rol rol = rolRepository.findById(2).orElseThrow();
            usuario.setRol(rol);
            OficinaSubgerencia oficinaSubgerencia = oficinaSubgerenciaRepository.findById(15).orElseThrow();
            usuario.setOficinaSubgerencia(oficinaSubgerencia);
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
    public UsuarioDTO updateUsuario(Integer userId, UsuarioDTO usuarioRequestDTO) { 
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        try {
             Optional<Usuario> userOptional = usuarioRepository.findById(userId);
             if(userOptional.isPresent()) {
                Usuario usuarioUpdate = userOptional.get();
                usuarioUpdate.setNombre(usuarioRequestDTO.getNombre());
                usuarioUpdate.setApellidos(usuarioRequestDTO.getApellidos());
                usuarioUpdate.setUserName(usuarioRequestDTO.getUserName());
                if(usuarioRequestDTO.getPassword() != null && !usuarioRequestDTO.getPassword().isEmpty()) {
                    // codificar la password
                    usuarioUpdate.setPassword(passwordEncoder.encode(usuarioRequestDTO.getPassword()));
                }
                usuarioUpdate.setCorreo(usuarioRequestDTO.getCorreo());

                Rol rol = rolRepository.findById(usuarioRequestDTO.getRolId()).orElseThrow();
                usuarioUpdate.setRol(rol);

                OficinaSubgerencia oficinaSubgerencia = oficinaSubgerenciaRepository.findById(usuarioRequestDTO.getOficinaSubgerenciaId())
                                                                                    .orElseThrow();
                usuarioUpdate.setOficinaSubgerencia(oficinaSubgerencia);

                Usuario newUsuario = usuarioRepository.save(usuarioUpdate);
                usuarioDTO.setUser(newUsuario);
                usuarioDTO.setStatusCode(200);
                usuarioDTO.setMessage("Usuario: " + userOptional.get().getNombre() + " actualizado con éxito");
             } else {
                usuarioDTO.setStatusCode(404);
                usuarioDTO.setMessage("Usuario: " + usuarioRequestDTO.getNombre() + " no encontrado");
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
            usuarioDTO.setMessage("Usuario encontrado con el id: " + usuarioById.getUsername() + " exitosamente");

        } catch (Exception e) {
            usuarioDTO.setStatusCode(500);
            usuarioDTO.setError("Ha ocurrido un error inesperado: " + e.getMessage());
            log.error("\nHa sucedido un crear el usuario", e.getCause() + "\n"); 
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
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuarioLoginDTO.getUserName(), usuarioLoginDTO.getPassword()));
            var user = usuarioRepository.findByUserName(usuarioLoginDTO.getUserName()).orElseThrow();
            var jwt = jwTokenService.generateTokenJWT(user);
            var refreshToken = jwTokenService.generateRefreshTokenJWT(new HashMap<>(), user);
            usuarioDTO.setStatusCode(200);
            usuarioDTO.setTokenJWT(jwt); 
            usuarioDTO.setRolId(user.getRol().getId());
            usuarioDTO.setRol(user.getRol().getNombre()); 
            usuarioDTO.setRefreshTokenJWT(refreshToken);
            usuarioDTO.setExpiracionTokenTime("24Hrs");
            usuarioDTO.setMessage("Usuario logeado con éxito, Rol " + user.getRol().getNombre());
            usuarioDTO.setDateTokenCreation(UtilsApp.getFormatoFechaActual());
            log.info("\nToken generado: " + usuarioDTO.getTokenJWT());

        } catch (Exception e) {
            usuarioDTO.setStatusCode(500);
            usuarioDTO.setMessage("Ha ocurrido un problema al logearse: " + e.getMessage());
            log.error("\nHa sucedido un error al logearse el usuario", e.getCause() + "\n"); 
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
                usuarioDTO.setDateTokenCreation(UtilsApp.getFormatoFechaActual()); 
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

    @Override
    public UsuarioDTO getAllUsuariosDTO() {
        
        UsuarioDTO requestDTO = new UsuarioDTO();
        
        try {
            List<Object[]> listResults = usuarioRepository.findAllUser();
            List<UsuarioDTO> listUsuarioDTO = new ArrayList<>();

            if(!listResults.isEmpty()) {
                for(Object[] object : listResults) {
                    UsuarioDTO usuarioDTO = new UsuarioDTO();
                    usuarioDTO.setStatusCode(200);
                    usuarioDTO.setNombre((String) object[0]);
                    usuarioDTO.setApellidos((String) object[1]);
                    usuarioDTO.setUserName((String) object[2]);
                    usuarioDTO.setCorreo((String) object[3]);
                    usuarioDTO.setRol((String) object[4]);
                    usuarioDTO.setOficina((String) object[5]);
                    listUsuarioDTO.add(usuarioDTO);
                }

                requestDTO.setStatusCode(200);
                requestDTO.setMessage("Listado de usuarios con éxito");
                requestDTO.setListUsuarioDTO(listUsuarioDTO);
            }
            return requestDTO;

        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setError("Ha ocurrido un error inexperado al listar los usuarios: " + e.getMessage());
            return requestDTO;
        }
    }
}
