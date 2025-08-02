package test.with.postgres

import grails.converters.JSON
import org.grails.web.servlet.mvc.GrailsWebRequest
import org.springframework.web.context.request.RequestContextHolder

import javax.servlet.http.HttpServletRequest

class JwtInterceptor {

    JwtService jwtService

    JwtInterceptor() {
        matchAll()
                .excludes(controller: 'auth')
    }

    boolean before() {
        def webRequest = RequestContextHolder.requestAttributes as GrailsWebRequest
        HttpServletRequest request = webRequest.getCurrentRequest()

        // Authorization header-аас token авах
        String token = jwtService.getToken(request)

        if (!token || !jwtService.validateToken(token)) {
            response.status = 401
            render([error: "Unauthorized or invalid token"] as JSON)
            return false
        }

        Map claims = jwtService.getClaimsFromToken(token)

        request.setAttribute("userId", claims.userId)
        request.setAttribute("branchId", claims.branchId)
        request.setAttribute("role", claims.role)

        return true
    }

    boolean after() {
        return true
    }

    void afterView() {
        // optional
    }
}
