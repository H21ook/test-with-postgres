package test.with.postgres.utils

import groovy.json.JsonOutput

class LoginResponse {
    CustomError error
    Map data;

    LoginResponse(Map data) {
        this.data = data;
    }

    LoginResponse(CustomError error) {
        this.error = error;
    }

    boolean hasError() {
        if (this.error) {
            return true
        }
        return false
    }

    Map getData() {
        return this.data
    }

    String toJson() {
        return JsonOutput.toJson(this.data)
    }
}
