package test.with.postgres.utils

class CustomError {
    String name
    String code

    CustomError(String name, String code) {
        this.name = name
        this.code = code
    }

    Map toMap() {
        [
                name  : name,
                code   : code
        ]
    }

    String toString() {
        println "Error name: $name"
    }
}
