package test.with.postgres

import groovy.json.JsonOutput
import test.with.postgres.utils.ErrorResponse

class UserController {

    UserService userService  // Autowired

    def index() {
        def users = userService.listUsers()
        render status: 200, contentType: 'application/json', text: JsonOutput.toJson(
            users.collect { it.toMap() }
        )
    }

    def show(Long id) {
        def user = userService.getUser(id)
        if (!user) {
            def errorResponse = new ErrorResponse('User not found', 'Not found')
            render status: 404, contentType: 'application/json', text: errorResponse.toJson()
            return
        }
        render status: 200, contentType: 'application/json', text: user.toJson()
        return
    }

    def save() {
        def user = userService.createUser(request.JSON)
        if (user.hasErrors()) {
            def errorResponse = new ErrorResponse('User create', 'Bad request', user.errors)
            render status: 400, contentType: 'application/json', text: errorResponse.toJson()
            return
        }

        render status: 201, contentType: 'application/json', text: user.toJson()
        return
    }

    def delete(Long id) {
        if (userService.deleteUser(id)) {
            // redirect(action: 'index')
            render status: 200, json: [message: 'User deleted successfully']
            return
        }

        render status: 404
        return
    }

}