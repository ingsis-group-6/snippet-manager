package ingsis.snippetmanager.domains.snippet.dto

import ingsis.snippetmanager.domains.snippet.model.Snippet
import ingsis.snippetmanager.domains.test.dto.TestDTO
import ingsis.snippetmanager.domains.test.model.Test
import java.util.*

class SnippetDTO {

    var id: UUID? = null
    var name: String? = null
    var type: String? = null
    var content: String? = null
    private var tests: List<TestDTO>? = null
    var ownerId: String? = null
    var createdAt: Date? = null

    constructor(
        name: String?,
        type: String?,
        content: String?,
        tests: List<Test>?,
        ownerId: String?,
        createdAt: Date?
    ) {
        this.name = name
        this.type = type
        this.content = content
        this.tests = tests!!.map { TestDTO(it.description, it.input, it.output, it.snippet!!.id) }
        this.ownerId = ownerId
        this.createdAt = createdAt
    }

    constructor(snippet: Snippet){
        this.name = snippet.name
        this.type = snippet.type
        this.content = snippet.content
        this.tests = snippet.tests.map { TestDTO(it.description, it.input, it.output, it.snippet!!.id) }
        this.ownerId = snippet.ownerId
        this.createdAt = snippet.createdAt
        this.id = snippet.id
    }
}