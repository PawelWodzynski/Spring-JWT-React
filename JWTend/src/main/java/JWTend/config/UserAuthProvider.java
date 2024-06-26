package JWTend.config;

import JWTend.DTO.UserDto;
import JWTend.service.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;

@Component
public class UserAuthProvider {

    private final String secretKey;
    private final UserService userService;

    public UserAuthProvider(@Value("${security.jwt.token.secret.key:secret-value}") String secretKey, UserService userService) {
        this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        this.userService = userService;
    }

    public String createToken(String login) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + 3_600_000); // 1 hour

        return JWT.create()
                .withIssuer(login)
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .sign(Algorithm.HMAC256(secretKey));
    }

    public Authentication validateToken(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretKey)).build();
        DecodedJWT decoded = verifier.verify(token);
        UserDto user = userService.findByLogin(decoded.getIssuer());
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }
}
