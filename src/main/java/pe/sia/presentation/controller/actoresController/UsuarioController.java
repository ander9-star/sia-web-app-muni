package pe.sia.presentation.controller.actoresController;

import org.springframework.web.bind.annotation.RestController;

import pe.sia.presentation.dto.actoresDTO.UsuarioDTO;
import pe.sia.service.interfaces.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/auth/register")
    public ResponseEntity<UsuarioDTO> createUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.createUsuario(usuarioDTO));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<UsuarioDTO> loginUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.login(usuarioDTO));
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<UsuarioDTO> refreshToken(@RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.refreshToken(usuarioDTO));
    }
    
    @GetMapping("/admin/get-all-users")
    public ResponseEntity<UsuarioDTO> getAllUsers() {
        return ResponseEntity.ok(usuarioService.getAllUsuariosDTO());
    }

    @GetMapping("/admin/get-user/{id}")
    public ResponseEntity<UsuarioDTO> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioService.getUsuarioById(id));
    }

    @PutMapping("/admin/update/{id}")
    public ResponseEntity<UsuarioDTO> updateUser(@PathVariable Integer id, @RequestBody UsuarioDTO usuario) {
        return ResponseEntity.ok(usuarioService.updateUsuario(id, usuario));
    }

    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<UsuarioDTO> deleteUser(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioService.deleteUsuario(id));
    }

    @GetMapping("/adminuser/get-profile")
    public ResponseEntity<UsuarioDTO> getProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String correo = authentication.getName();
        UsuarioDTO usuarioDTO = usuarioService.getMyProfile(correo);
        return ResponseEntity.status(usuarioDTO.getStatusCode()).body(usuarioDTO);
    }  

}
