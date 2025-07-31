package test.with.postgres
import grails.converters.JSON

class BootStrap {

    def init = { servletContext ->
        JSON.registerObjectMarshaller(User) { User user ->
            return user.toMap()
        }
    }
    def destroy = {
    }
}
