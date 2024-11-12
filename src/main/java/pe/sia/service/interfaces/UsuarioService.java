package pe.sia.service.interfaces;


import pe.sia.presentation.dto.actoresDTO.UsuarioDTO;

public interface UsuarioService {

    UsuarioDTO createUsuario(UsuarioDTO usuarioCreateDTO);
    UsuarioDTO updateUsuario(Integer idUsuario, UsuarioDTO usuarioRequestDTO);
    UsuarioDTO deleteUsuario(Integer idUsuario);
    UsuarioDTO getUsuarioById(Integer idUsuario);
    UsuarioDTO getAllUsuarios();
    UsuarioDTO login(UsuarioDTO usuarioLoginDTO);
    UsuarioDTO refreshToken(UsuarioDTO usuarioRefreshDTO);
    UsuarioDTO getMyProfile(String userName);
    UsuarioDTO getAllUsuariosDTO();

}
