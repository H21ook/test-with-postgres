package test.with.postgres.utils

import org.mindrot.jbcrypt.BCrypt

class PasswordUtil {
    static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt())
    }

    static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword)
    }
}
