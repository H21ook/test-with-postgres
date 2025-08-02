package test.with.postgres

import grails.testing.web.interceptor.InterceptorUnitTest
import spock.lang.Specification

class JwtInterceptorSpec extends Specification implements InterceptorUnitTest<JwtInterceptor> {

    def setup() {
    }

    def cleanup() {

    }

    void "Test jwt interceptor matching"() {
        when:"A request matches the interceptor"
            withRequest(controller:"jwt")

        then:"The interceptor does match"
            interceptor.doesMatch()
    }
}
