package pe.sia.service.implementation;

import java.util.*;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final OficinaSubgerenciaRepository oficinaSubgerenciaRepository;
    private final RolRepository rolRepository;
    private final JWTokenUtils jwTokenService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, OficinaSubgerenciaRepository oficinaSubgerenciaRepository,
                              RolRepository rolRepository, JWTokenUtils jwTokenService, AuthenticationManager authenticationManager,
                              PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.oficinaSubgerenciaRepository = oficinaSubgerenciaRepository;
        this.rolRepository = rolRepository;
        this.jwTokenService = jwTokenService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UsuarioDTO createUsuario(UsuarioDTO usuarioCreateDTO) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        try {
            Usuario usuario = new Usuario();
            usuario.setNombre(usuarioCreateDTO.getNombre());
            usuario.setApellidos(usuarioCreateDTO.getApellidos());
            usuario.setUserName(usuarioCreateDTO.getUserName().trim());
            usuario.setPassword(passwordEncoder.encode(usuarioCreateDTO.getPassword().trim()));
            usuario.setCorreo(usuarioCreateDTO.getCorreo());
            Rol rol = rolRepository.findById(2).orElseThrow();
            usuario.setRol(rol);
            OficinaSubgerencia oficinaSubgerencia = oficinaSubgerenciaRepository.findById(15).orElseThrow();
            usuario.setOficinaSubgerencia(oficinaSubgerencia);
            usuario.setEstado(Boolean.FALSE);
            usuario = usuarioRepository.save(usuario);

            if (usuario.getId() > 0) {
                usuarioDTO.setUser((usuario));
                usuarioDTO.setStatusCode(201);
                usuarioDTO.setMessage("Usuario creado: " + usuario.getNombre() + " con éxito");
            }

        } catch (Exception e) {
            usuarioDTO.setStatusCode(500);
            usuarioDTO.setError("Ha ocurrido un error inesperado al crear el usuario: " + e.getMessage());
        }
        return usuarioDTO;
    }

    @Override
    public UsuarioDTO updateUsuario(Integer userId, UsuarioDTO usuarioRequestDTO, Authentication authentication) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        try {
            // obtener el usuario actual
            Usuario usuarioActual = (Usuario) authentication.getPrincipal();
            Optional<Usuario> userOptional = usuarioRepository.findById(userId);
            if (userOptional.isPresent()) {
                Usuario usuarioUpdate = userOptional.get();
                boolean esElMismoUsuarioActual = usuarioActual.getId().equals(userId);
                usuarioUpdate.setNombre(usuarioRequestDTO.getNombre() == null ? usuarioUpdate.getNombre() : usuarioRequestDTO.getNombre().trim());
                usuarioUpdate.setApellidos(usuarioRequestDTO.getApellidos() == null ? usuarioUpdate.getApellidos() : usuarioRequestDTO.getApellidos().trim());
                usuarioUpdate.setUserName(usuarioRequestDTO.getUserName() == null ? usuarioUpdate.getUsername() : usuarioRequestDTO.getUserName().trim());
                // si la password no esta vacia
                if (usuarioRequestDTO.getPassword() != null && !usuarioRequestDTO.getPassword().trim().isEmpty()) {
                    // codificar la password
                    usuarioUpdate.setPassword(passwordEncoder.encode(usuarioRequestDTO.getPassword()));
                } else {
                    usuarioUpdate.setPassword(usuarioUpdate.getPassword().trim());
                }
                usuarioUpdate.setCorreo(usuarioRequestDTO.getCorreo() == null ? usuarioUpdate.getCorreo() : usuarioRequestDTO.getCorreo().trim());
                Rol rol = rolRepository.findById(usuarioRequestDTO.getRolId()).orElseThrow();
                usuarioUpdate.setRol(rol);

                OficinaSubgerencia oficinaSubgerencia = oficinaSubgerenciaRepository.findById(usuarioRequestDTO.getOficinaSubgerenciaId())
                        .orElseThrow();
                usuarioUpdate.setOficinaSubgerencia(oficinaSubgerencia);
                usuarioUpdate.setEstado(usuarioRequestDTO.getEstado() != null ? usuarioRequestDTO.getEstado() : Boolean.TRUE);

                Usuario newUsuario = usuarioRepository.save(usuarioUpdate);
                boolean haCambiadoUsername = !Objects.equals(usuarioActual.getUsername(), newUsuario.getUsername());
                // Solo generar token si es el mismo usuario o un admin actualizando su propio perfil
                boolean generarNuevoToken = (esElMismoUsuarioActual && haCambiadoUsername);

                // Generar token solo si cumple las condiciones
                if (generarNuevoToken) {
                    var jwt = jwTokenService.generateTokenJWT(newUsuario);
                    var refreshToken = jwTokenService.generateRefreshTokenJWT(new HashMap<>(), newUsuario);
                    usuarioDTO.setTokenJWT(jwt);
                    usuarioDTO.setRefreshTokenJWT(refreshToken);
                    usuarioDTO.setExpiracionTokenTime("24Hrs");
                    usuarioDTO.setDateTokenCreation(UtilsApp.getFormatoFechaActual());
                }
                usuarioDTO.setUser(newUsuario);
                usuarioDTO.setStatusCode(201);
                usuarioDTO.setMessage("Usuario actualizado: " + userOptional.get().getNombre() + " con éxito");
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

    private boolean usuarioTieneIncidencias(Integer userId) {
        Optional<Boolean> findUser = usuarioRepository.findUserIncidencia(userId);
        return findUser.isPresent() && Boolean.TRUE.equals(findUser.get());
    }

    private UsuarioDTO crearRespuestaUsuarioConIncidencias(UsuarioDTO usuarioRequestDTO) {
        usuarioRequestDTO.setStatusCode(404);
        usuarioRequestDTO.setMessage("Usuario tiene problema/fallos registrados");
        return usuarioRequestDTO;
    }

    @Override
    public UsuarioDTO deleteUsuario(Integer idUsuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        try {
            if(usuarioTieneIncidencias(idUsuario)) {
                return crearRespuestaUsuarioConIncidencias(usuarioDTO);
            } else {
                Optional<Usuario> userOptional = usuarioRepository.findById(idUsuario);
                if (userOptional.isPresent()) {
                    usuarioRepository.deleteById(idUsuario);
                    usuarioDTO.setStatusCode(204);
                    usuarioDTO.setMessage("Usuario eliminado con éxito");
                } else {
                    usuarioDTO.setStatusCode(404);
                    usuarioDTO.setMessage("El Usuario no ha sido encontrado");
                }
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
            Optional<Usuario> usuarioById = usuarioRepository.findById(idUsuario);
            if(usuarioById.isPresent()) {
                Usuario usuario = usuarioById.get();
                usuarioDTO.setUser(usuario);
                usuarioDTO.setStatusCode(200);
                usuarioDTO.setMessage("Usuario encontrado con el id: " + usuario.getUsername() + " exitosamente");
            } else {
                usuarioDTO.setStatusCode(404);
                usuarioDTO.setMessage("Usuario no encontrado");
            }
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
            if (!userList.isEmpty()) {
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
            if (Boolean.TRUE.equals(user.getEstado())) {
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
            } else {
                usuarioDTO.setStatusCode(403);
                usuarioDTO.setMessage("Usuario inactivo");
            }
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

            if (jwTokenService.isTokenValid(usuarioRefreshDTO.getTokenJWT(), usuario)) {
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
            if (userOptionalName.isPresent()) {
                userOptionalName.ifPresent(user -> {
                    requestDTO.setIdUsuario(user.getId());
                    requestDTO.setNombre(user.getNombre());
                    requestDTO.setApellidos(user.getApellidos());
                    requestDTO.setUserName(user.getUsername());
                    requestDTO.setCorreo(user.getCorreo());
                    requestDTO.setRol(user.getRol().getNombre());
                    requestDTO.setRolId(user.getRol().getId());
                    requestDTO.setOficina(user.getOficinaSubgerencia().getNombre());
                    requestDTO.setOficinaSubgerenciaId(user.getOficinaSubgerencia().getId());
                    requestDTO.setEstado(user.getEstado());
                    requestDTO.setStatusCode(200);
                    requestDTO.setMessage("Successful");
                });
            } else {
                requestDTO.setStatusCode(404);
                requestDTO.setMessage("Usuario no encontrado");
            }
        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setError("Ha ocurrido un error al actualizar el usuario: " + e.getMessage());
        }

        return requestDTO;
    }

    @Override
    public UsuarioDTO getAllUsuariosDTO() {
        UsuarioDTO requestDTO = new UsuarioDTO();
        try {
            List<Object[]> listResults = usuarioRepository.findAllUser();
            List<UsuarioDTO> listUsuarioDTO = new ArrayList<>();
            if (!listResults.isEmpty()) {
                for (Object[] object : listResults) {
                    UsuarioDTO usuarioDTO = new UsuarioDTO();
                    usuarioDTO.setStatusCode(200);
                    usuarioDTO.setIdUsuario((Integer) object[0]);
                    usuarioDTO.setNombre((String) object[1]);
                    usuarioDTO.setApellidos((String) object[2]);
                    usuarioDTO.setUserName((String) object[3]);
                    usuarioDTO.setCorreo((String) object[5]);
                    usuarioDTO.setRol((String) object[6]);
                    usuarioDTO.setRolId((Integer) object[7]);
                    usuarioDTO.setOficina((String) object[8]);
                    usuarioDTO.setOficinaSubgerenciaId((Integer) object[9]);
                    usuarioDTO.setEstado((Boolean) object[10]);
                    usuarioDTO.setCantidadProblemasRegistrados((Integer) object[11]);
                    usuarioDTO.setCantidadDetalleProblemas((Integer) object[12]);
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
