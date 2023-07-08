package ingsis.snippetmanager.domains.test.dto

import ingsis.snippetmanager.domains.snippet.dto.SnippetDTO
import ingsis.snippetmanager.domains.test.model.Test
import java.util.*

class TestDTO {

    var description: String? = null
    var input: List<String?> = listOf()
    var output: List<String?> = listOf()
    var snippetId: UUID? = null
    var id: UUID? = null
    var ownerId: String? = null
    var snippet: SnippetDTO? = null

    constructor(description: String?, input: List<String?>, output: List<String?>, snippetId: UUID?, id: UUID?, ownerId: String?) {
        this.description = description
        this.input = input
        this.output = output
        this.snippetId = snippetId
        this.id = id
        this.ownerId = ownerId
    }



    constructor(test: Test){
        this.description = test.description
        this.input = parseStringToList(test.input!!)
        this.output = parseStringToList(test.output!!)
        this.snippetId = test.snippet!!.id
        this.id = test.id
        this.ownerId = test.ownerId
        this.snippet = SnippetDTO(test.snippet!!)
    }


    private fun parseStringToList(string: String): List<String?> {
        return string.substring(1,string.length-1).split(",").map { it.trim() }
    }

}