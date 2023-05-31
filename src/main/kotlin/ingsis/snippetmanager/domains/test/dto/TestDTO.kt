package ingsis.snippetmanager.domains.test.dto

import java.util.*

class TestDTO {

    var description: String? = null
    var input: String? = null
    var output: String? = null
    var snippetId: UUID? = null

    constructor(description: String?, input: String?, output: String?, snippetId: UUID?) {
        this.description = description
        this.input = input
        this.output = output
        this.snippetId = snippetId
    }

}