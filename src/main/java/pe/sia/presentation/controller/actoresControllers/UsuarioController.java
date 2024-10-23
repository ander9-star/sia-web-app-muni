package pe.sia.presentation.controller.actoresControllers;

import org.springframework.web.bind.annotation.RestController;

import pe.sia.persistence.entity.actores.Usuario;
import pe.sia.presentation.dto.actoresDTO.UsuarioDTO;
import pe.sia.service.implementation.UsuarioServiceImpl;

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
    private UsuarioServiceImpl usuarioServiceImpl;

    @PostMapping("/auth/register")
    public ResponseEntity<UsuarioDTO> createUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioServiceImpl.createUsuario(usuarioDTO));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<UsuarioDTO> loginUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioServiceImpl.login(usuarioDTO));
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<UsuarioDTO> refreshToken(@RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioServiceImpl.refreshToken(usuarioDTO));
    }
    
    @GetMapping("/admin/get-all-users")
    public ResponseEntity<UsuarioDTO> getAllUsers() {
        return ResponseEntity.ok(usuarioServiceImpl.getAllUsuarios());
    }

    @GetMapping("/admin/get-user/{id}")
    public ResponseEntity<UsuarioDTO> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioServiceImpl.getUsuarioById(id));
    }

    @PutMapping("/admin/update/{id}")
    public ResponseEntity<UsuarioDTO> updateUser(@PathVariable Integer id, @RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioServiceImpl.updateUsuario(id, usuario));
    }

    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<UsuarioDTO> deleteUser(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioServiceImpl.deleteUsuario(id));
    }

    @GetMapping("/adminuser/get-profile")
    public ResponseEntity<UsuarioDTO> getProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String correo = authentication.getName();
        UsuarioDTO usuarioDTO = usuarioServiceImpl.getMyProfile(correo);
        return ResponseEntity.status(usuarioDTO.getStatusCode()).body(usuarioDTO);
    }  
}
