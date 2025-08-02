package test.with.postgres

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import grails.core.GrailsApplication

import javax.servlet.http.HttpServletRequest

class JwtService {
    GrailsApplication grailsApplication

    Algorithm getAlgorithm() {
        def secret = grailsApplication.config.getProperty("jwt.secret", String)
        return Algorithm.HMAC256(secret)
    }

    String generateToken(Map user, int expireInMinutes = 60) {
        def now = new Date()
        def expiry = new Date(now.time + (expireInMinutes * 60 * 1000))

        return JWT.create()
                .withSubject(user.id)
                .withClaim("branchId", user.branchId)
                .withClaim("role", user.role)
                .withIssuedAt(now)
                .withExpiresAt(expiry)
                .sign(getAlgorithm())
    }

    Map getClaimsFromToken(String token) {
        def verifier = JWT.require(getAlgorithm()).build()
        def decodedJWT = verifier.verify(token)
        return [
                userId   : decodedJWT.subject,
                branchId : decodedJWT.getClaim("branchId")?.asString(),
                role     : decodedJWT.getClaim("role")?.asString()
        ]
    }

    String getToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization")
        if (authHeader?.startsWith("Bearer ")) {
            return authHeader.replace("Bearer ", "")
        }
        return null
    }

    boolean validateToken(String token) {
        try {
            JWT.require(getAlgorithm()).build().verify(token)
            return true
        } catch (Exception e) {
            return false
        }
    }
}
