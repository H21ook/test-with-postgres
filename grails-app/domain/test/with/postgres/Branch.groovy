package test.with.postgres
import test.with.postgres.JsonSerializable

class Branch implements JsonSerializable {
    String name
    Long parentId
    String path
    Date dateCreated
    Date lastUpdated

    static constraints = {
        name blank: false, unique: ['parentId']
        path blank: false
        parentId nullable: true
    }

    @Override
    Map toMap() {
        return [
                id          : id,
                name        : name,
                parentId    : parentId,
                path        : path,
                dateCreated : dateCreated
        ]
    }
}
