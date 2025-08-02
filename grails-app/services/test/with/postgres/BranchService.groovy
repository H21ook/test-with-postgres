package test.with.postgres

import grails.gorm.transactions.Transactional
import test.with.postgres.utils.CustomError

@Transactional
class BranchService {

    def createBranch(Map data) {
        Branch branchData = new Branch(data)
        Long branchId = branchData.getParentId()
        if (branchId != null) {
            Branch parent = Branch.get(branchId)
            if (parent) {
                branchData.setPath(parent.getPath() + "/")
                println "parent id ${parent.name}"
            }
        } else {
            branchData.setPath("/")
        }

        if (!branchData.validate()) {
            return branchData
        }

        branchData.save(flush: true)

        return branchData
    }

    def getBranches() {
        return Branch.list()
    }

    Branch getBranch(Long id) {
        return Branch.get(id)
    }

    def getParentBranchWithChildren(Long parentId) {
        println "parentId $parentId"
        Branch branch = Branch.get(parentId)
        if (branch == null) {
            return null
        }
        List<Branch> results = Branch.findAllByPathIlike('%' + branch.getId() + '%')

        return [
                branch  : branch.toMap(),
                children: results.collect { it.toMap() }
        ];
    }
}
