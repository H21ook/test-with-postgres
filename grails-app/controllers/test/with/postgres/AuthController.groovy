package test.with.postgres

import test.with.postgres.utils.ErrorResponse

class AuthController {

    AuthService authService

    def login() {
        def json = request.JSON

        def workerId = json.workerId
        def password = json.password
        def res = authService.login(workerId, password)
        if (res.hasError()) {
            def errorResponse = new ErrorResponse(res.error, message(code: res.error.code))
            render status: 400 , contentType: "application/json", text: errorResponse.toJson()
            return
        }

        render status: 200, contentType: "application/json", text: res.toJson()
    }
}
