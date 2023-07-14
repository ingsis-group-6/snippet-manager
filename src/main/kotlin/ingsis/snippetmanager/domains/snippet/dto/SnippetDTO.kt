package ingsis.snippetmanager.domains.snippet.dto

import ingsis.snippetmanager.domains.rule.model.ComplianceState
import ingsis.snippetmanager.domains.snippet.model.Snippet
import ingsis.snippetmanager.domains.test.dto.CreateTestDTO
import ingsis.snippetmanager.domains.test.model.Test
import java.util.*

class SnippetDTO {

    var id: UUID? = null
    var name: String? = null
    var type: String? = null
    var content: String? = null
    var compliance: ComplianceState? = null
    var tests: List<CreateTestDTO>? = null
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
        this.tests = tests!!.map { CreateTestDTO(it.description, parseStringToList(it.input!!), parseStringToList(it.output!!), it.snippet!!.id) }
        this.ownerId = ownerId
        this.createdAt = createdAt
        this.compliance = ComplianceState.PENDING
    }

    constructor(snippet: Snippet){
        this.name = snippet.name
        this.type = snippet.type
        this.content = snippet.content
        this.tests = snippet.tests.map { CreateTestDTO(it.description, parseStringToList(it.input!!), parseStringToList(it.output!!), it.snippet!!.id) }
        this.ownerId = snippet.ownerId
        this.createdAt = snippet.createdAt
        this.id = snippet.id
        this.compliance = snippet.compliance
    }

    private fun parseStringToList(string: String): List<String?> {
        return string.substring(1,string.length-1).split(",").map { it.trim() }
    }
}