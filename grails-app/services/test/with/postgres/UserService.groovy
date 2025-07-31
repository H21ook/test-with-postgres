package test.with.postgres

import grails.gorm.transactions.Transactional
import test.with.postgres.utils.PasswordUtil

@Transactional
class UserService {

    User createUser(Map params) {
        def userData = new User(params)
        def existing = User.findByWorkerId(userData.workerId)
        if (existing) {
            userData.errors.rejectValue('workerId', 'user.workerId.unique', 'This workerId already exists.')
            return userData
        }

        if(!userData.validate()) {
            return userData
        }
        userData.password = PasswordUtil.hashPassword(params.password)

        userData.save(flush: true)

        return userData
    }

    List<User> listUsers() {
        return User.list()
    }

    User getUser(Long id) {
        return User.get(id)
    }

    boolean deleteUser(Long id) {
        def user = User.get(id)
        if (user) {
            user.delete(flush: true)
            return true
        }
        return false
    }

}
