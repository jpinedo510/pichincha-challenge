package pe.msauth.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;

public class TokenUtil {

    private static final int TOKEN_EXPIRATION_TIME = 1800000;

    public static String encodeToken(Claims claims) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.ES256;
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        HashMap<String, Object> header = new HashMap<String, Object>();
        header.put("alg", signatureAlgorithm.toString());
        header.put("typ", "JWT");

        JwtBuilder jwtBuilderToken = Jwts
                .builder()
                .setHeader(header)
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME)).signWith(key);

        return jwtBuilderToken.compact();
    }
}
