package ingsis.snippetmanager.domains.snippet.dto

import ingsis.snippetmanager.domains.snippet.model.Snippet
import java.util.*

class UpdateSnippetDTO {
        var id: UUID? = null
        var content: String? = null

        constructor(
            id: UUID?,
            content: String?,
        ) {
            this.content = content
        }

        constructor(snippet: Snippet){
            this.content = snippet.content
            this.id = snippet.id
        }
}