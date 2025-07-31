package test.with.postgres
import groovy.json.JsonOutput

trait JsonSerializable {

    abstract Map toMap()

    String toJson() {
        return JsonOutput.toJson(toMap())
    }
}