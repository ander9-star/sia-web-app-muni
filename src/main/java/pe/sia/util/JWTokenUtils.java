package pe.sia.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JWTokenUtils {
    
    private final SecretKey secretKey;
    private static final long EXPIRATION_TIME = 86400000; // son en milisegundos lo que equivale a 24 horas

    public JWTokenUtils() {
        //clave a encriptar en base64
        String secretString = "843567893696976453275974432697R634976R738467TR678T34865R6834R8763T478378637664538745673865783678548735687R3";
        byte[] keyBytes = Base64.getDecoder().decode(secretString.getBytes(StandardCharsets.UTF_8));
        // Se crea la clave secreta, con un algoritmo de encriptación HMAC y el tipo de clave
        this.secretKey = new SecretKeySpec(keyBytes, "HmacSHA256");
    }

    // Generar token JWT, con los detalles del usuario (username) y la fecha de emisión y expiración
    public String generateTokenJWT(UserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(secretKey)
                .compact();
    }

    // Refrescar token JWT, con los detalles del usuario (username) y la fecha de emisión y expiración
    public String generateRefreshTokenJWT(Map<String, Object> claims, UserDetails userDetails) {
        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(secretKey)
                .compact();
    }

    // Extraer el nombre de usuario del token JWT
    public String extraerUsername(String token) {
        return extraerReclamo(token, Claims::getSubject);
    }

    // Creando una funcion generica para extraer reclamos del token
    private <T> T extraerReclamo(String token, Function<Claims, T> claimsTFunction) {
        return claimsTFunction.apply(Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload());
    }

    // Verificar si el token JWT es válido
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extraerUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpirado(token));
    }

    // Verificar si el token JWT ha expirado
    private boolean isTokenExpirado(String token){
        return extraerReclamo(token, Claims::getExpiration).before(new Date());
    }
}
