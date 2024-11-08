package antonio.lista_viaggi_utenti.tools;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JWT {

    @Value("${jwt.secret}")
    private String secret;


    private Key getKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String createToken(String userId, String role) {
        return Jwts.builder()
                .setSubject(userId)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public void verifyToken(String token) {
        Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token);
    }
}
