package test.with.postgres

import grails.gorm.transactions.Transactional
import test.with.postgres.utils.CustomError
import test.with.postgres.utils.LoginResponse
import test.with.postgres.utils.PasswordUtil

@Transactional
class AuthService {

    JwtService jwtService
    LoginResponse login(String workerId, String password) {
        def foundUser = User.findByWorkerId(workerId);
        if (foundUser == null) {
            return new LoginResponse(new CustomError("Login", "login.notRegistered"))
        }

        if (!PasswordUtil.checkPassword(password, foundUser.password)) {
            return new LoginResponse(new CustomError("Login", "login.invalidCredentials"))
        }

        def token = jwtService.generateToken([
                id: foundUser.id.toString(),
                role: foundUser.role,
                branchId: foundUser.branchId
        ])

        return new LoginResponse([
                token: token,
                user : foundUser.toMap()
        ])
    }
}
