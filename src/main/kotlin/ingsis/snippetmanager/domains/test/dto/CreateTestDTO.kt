package ingsis.snippetmanager.domains.test.dto

import ingsis.snippetmanager.domains.snippet.dto.SnippetDTO
import ingsis.snippetmanager.domains.test.model.Test
import java.util.*

class CreateTestDTO {

    var description: String? = null
    var input: List<String?> = listOf()
    var output: List<String?> = listOf()
    var snippetId: UUID? = null
    private var snippet: SnippetDTO? = null

    constructor(description: String?, input: List<String?>, output: List<String?>, snippetId: UUID?) {
        this.description = description
        this.input = input
        this.output = output
        this.snippetId = snippetId
        this.snippet = null
    }

    constructor(test: Test){
        this.description = test.description
        this.input = parseStringToList(test.input!!)
        this.output = parseStringToList(test.output!!)
        this.snippetId = test.snippet!!.id
        this.snippet = SnippetDTO(test.snippet!!)
    }

    private fun parseStringToList(string: String): List<String?> {
        return string.substring(1,string.length-1).split(",").map { it.trim() }
    }

}