package test.with.postgres.utils

import groovy.json.JsonOutput
import org.springframework.validation.Errors
import org.springframework.validation.ObjectError

class ErrorResponse {

    String errorMessage

    ErrorResponse(String name, String errorMessage, Errors validationErrors) {
        this.errorMessage = errorMessage
        println '########## ERRORS START ##########'
        println "Error name: $name, errorMessage: $errorMessage"

//        def firstError = validationErrors.allErrors?.first()

        validationErrors.allErrors.each { ObjectError err ->
            def field = err.metaClass.hasProperty(err, 'field') ? err.field : 'global'
            println "Field: $field, Code: ${err.code}, Args: ${err.arguments}"
        }
        println '########## ERRORS END ##########'
    }

    ErrorResponse(String name, String errorMessage) {
        this.errorMessage = errorMessage
        println "Error name: $name, errorMessage: $errorMessage"
    }

    ErrorResponse(CustomError error, String errorMessage) {
        this.errorMessage = errorMessage
        println "Error name: $error.name, errorMessage: $errorMessage"
    }

    String toJson() {
        return JsonOutput.toJson([
            message: this.errorMessage
        ])
    }

}
