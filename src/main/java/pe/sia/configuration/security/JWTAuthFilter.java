package pe.sia.configuration.security;

import java.io.IOException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.sia.service.details.UsuarioDetailsService;
import pe.sia.util.JWTokenUtils;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {

    private final JWTokenUtils jwTokenUtils;
    private final UsuarioDetailsService usuarioDeatilsService;

    public JWTAuthFilter(JWTokenUtils tokenUtils, UsuarioDetailsService usuarioDeatilsService) {
        this.jwTokenUtils = tokenUtils;
        this.usuarioDeatilsService = usuarioDeatilsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String userName;

        // Verifica si el encabezado de autorización está presente
        if (authHeader == null || authHeader.isBlank()) {
            filterChain.doFilter(request, response);
            return;
        }

        // Extraer el token JWT del encabezado
        jwtToken = authHeader.substring(7); // Eliminar "Bearer " del encabezado
        userName = jwTokenUtils.extraerUsername(jwtToken);

        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                UserDetails userDetails = usuarioDeatilsService.loadUserByUsername(userName);
                // verificando si el token es valido
                if (jwTokenUtils.isTokenValid(jwtToken, userDetails)) {
                    SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities()
                    );
                    token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    securityContext.setAuthentication(token);
                    SecurityContextHolder.setContext(securityContext);
                }
            } catch (UsernameNotFoundException e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Usuario no encontrado");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
