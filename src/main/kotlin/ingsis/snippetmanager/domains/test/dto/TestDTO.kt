package ingsis.snippetmanager.domains.test.dto

import ingsis.snippetmanager.domains.snippet.dto.SnippetDTO
import ingsis.snippetmanager.domains.test.model.Test
import java.util.*

class TestDTO {

    var description: String? = null
    var input: String? = null
    var output: String? = null
    var snippetId: UUID? = null
    var snippet: SnippetDTO? = null

    constructor(description: String?, input: String?, output: String?, snippetId: UUID?) {
        this.description = description
        this.input = input
        this.output = output
        this.snippetId = snippetId
        this.snippet = null
    }

    constructor(test: Test){
        this.description = test.description
        this.input = test.input
        this.output = test.output
        this.snippetId = test.snippet!!.id
        this.snippet = SnippetDTO(test.snippet!!)
    }

}