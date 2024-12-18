package pe.sia.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pe.sia.service.details.UsuarioDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    // Servicio de detalles del usuario para autenticación
    private final UsuarioDetailsService usuarioDetailsService;

    // Filtro de autenticación JWT
    private final JWTAuthFilter jwtAuthFilter;

    public SecurityConfig(UsuarioDetailsService usuarioDetailsService, JWTAuthFilter jwtAuthFilter) {
        this.usuarioDetailsService = usuarioDetailsService;
        this.jwtAuthFilter = jwtAuthFilter;
    }

    // Este metodo configura la cadena de filtros de seguridad para la aplicación
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // Configuración de seguridad para la aplicación
        httpSecurity.csrf(AbstractHttpConfigurer::disable) // Desactiva CSRF
                .cors(Customizer.withDefaults()) // Configuración de CORS por defecto
                .authorizeHttpRequests(request -> request.requestMatchers("/auth/**", "/public/**").permitAll() // Rutas permitidas sin autenticación
                        .requestMatchers("/admin/**").hasAnyAuthority("ADMIN") // Rutas para administradores
                        .requestMatchers("/soporte-tecnico").hasAnyAuthority("SOPORTE_TECNICO") // Rutas para soporte técnico
                        .requestMatchers("/adminuser/**").hasAnyAuthority("ADMIN", "SOPORTE_TECNICO") // Rutas para usuarios administrativos
                        .anyRequest().authenticated()) // Todas las demás solicitudes requieren autenticación
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Política de sesión sin estado
                .authenticationProvider(authenticationProvider()).addFilterBefore(
                        jwtAuthFilter, UsernamePasswordAuthenticationFilter.class // Filtro de autenticación JWT antes del filtro de autenticación de usuario y contraseña
                );
        // construye y devuelve la cadena de filtros de seguridad configurada, para aplicar las reglas de seguridad en la app
        return httpSecurity.build();
    }

    @Bean
    AuthenticationProvider authenticationProvider(){
        // Proveedor de autenticación que utiliza el servicio de detalles del usuario y el codificador de contraseña
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(usuarioDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        // Codificador de contraseña utilizando BCrypt
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        // Gestor de autenticación
        return authenticationConfiguration.getAuthenticationManager();
    }
}
