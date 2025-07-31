package test.with.postgres

import grails.gorm.transactions.Transactional
import test.with.postgres.utils.CustomError
import test.with.postgres.utils.LoginResponse
import test.with.postgres.utils.PasswordUtil
import test.with.postgres.utils.JwtUtil

@Transactional
class AuthService {

    LoginResponse login(String workerId, String password) {
        def foundUser = User.findByWorkerId(workerId);
        if (foundUser == null) {
            return new LoginResponse(new CustomError("Login", "login.notRegistered"))
        }

        if (!PasswordUtil.checkPassword(password, foundUser.password)) {
            return new LoginResponse(new CustomError("Login", "login.invalidCredentials"))
        }

        def token = JwtUtil.generateToken(foundUser.id.toString())

        return new LoginResponse([
                token: token,
                user : foundUser.toMap()
        ])
    }
}
