package test.with.postgres
import test.with.postgres.JsonSerializable

class User implements JsonSerializable {

    String workerId
    String surname
    String givenname
    String position
    String rank
    String password
    String role
    Date dateCreated
    Date lastUpdated

    static constraints = {
        workerId blank: false, unique: true
        surname nullable: true
        givenname nullable: true
        position nullable: true
        rank nullable: true
        password blank: false, nullable: false
        role nullable: true
    }

    static mapping = {
        table 'user_table'
        password column: 'user_password' // PostgreSQL зарим үед "password" зарлагдсан keyword байж болдог тул
    }

    Map toMap() {
        return [
            id        : id,
            workerId  : workerId,
            surname   : surname,
            givenname : givenname,
            position  : position,
            rank      : rank,
            role      : role
        ]
    }
}
