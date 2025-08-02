package test.with.postgres

import groovy.json.JsonOutput
import test.with.postgres.utils.ErrorResponse

class BranchController {

    BranchService branchService
    def index() {
        def branches = branchService.listUsers()
        render status: 200, contentType: 'application/json', text: JsonOutput.toJson(
                branches.collect { it.toMap() }
        )
    }

    def save() {
        def branch = branchService.createBranch(request.JSON)
        if (branch.hasErrors()) {
            def errorResponse = new ErrorResponse('Branch create', 'Bad request', branch.errors)
            render status: 400, contentType: 'application/json', text: errorResponse.toJson()
            return
        }

        render status: 201, contentType: 'application/json', text: branch.toJson()
    }

    def getBranchesByParentId(Long id) {
        def result = branchService.getParentBranchWithChildren(id)
        render status: 200, contentType: 'application/json', text: JsonOutput.toJson(result)
    }

}
