package test.with.postgres

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm

class JwtService {
    static final String SECRET = ""
    static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET)

    static String generateToken(String subject, int expireInMinutes = 60) {
        def now = new Date()
        def expiry = new Date(now.time + (expireInMinutes * 60 * 1000))

        return JWT.create()
                .withSubject(subject)
                .withIssuedAt(now)
                .withExpiresAt(expiry)
                .sign(ALGORITHM)
    }

    static String verifyAndGetSubject(String token) {
        def verifier = JWT.require(ALGORITHM).build()
        def decodedJWT = verifier.verify(token)
        return decodedJWT.subject
    }
}
