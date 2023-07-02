package ingsis.snippetmanager.domains.snippet.dto

import ingsis.snippetmanager.domains.snippet.model.Snippet
import java.util.*

class UpdateSnippetDTO {
        var id: UUID? = null
        var name: String? = null
        var type: String? = null
        var content: String? = null
        var ownerId: String? = null
        var createdAt: Date? = null

        constructor(
            name: String?,
            type: String?,
            content: String?,
            ownerId: String?,
            createdAt: Date?
        ) {
            this.name = name
            this.type = type
            this.content = content
            this.ownerId = ownerId
            this.createdAt = createdAt
        }

        constructor(snippet: Snippet){
            this.name = snippet.name
            this.type = snippet.type
            this.content = snippet.content
            this.ownerId = snippet.ownerId
            this.createdAt = snippet.createdAt
            this.id = snippet.id
        }
}