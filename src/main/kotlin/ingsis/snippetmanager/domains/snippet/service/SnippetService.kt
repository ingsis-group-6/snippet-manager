package ingsis.snippetmanager.domains.snippet.service

import ingsis.snippetmanager.domains.snippet.dto.SnippetDTO
import ingsis.snippetmanager.domains.snippet.model.Snippet
import java.util.*

interface SnippetService {

    fun createSnippet(snippet: SnippetDTO, userId: String): Snippet
    fun updateSnippet(snippet:Snippet, userId: String): Snippet
    fun deleteSnippet(id: UUID, userId: String)
    fun getSnippetById(id: UUID, token: String, userId: String): Snippet
    fun getSnippetsByUserId(userId: String): List<Snippet>
    fun getAllSnippetsByUserId(userId: String): List<Snippet>
    fun getSnippetsByUserIdAndSnippetId(userId: String, snippets: List<UUID>): List<Snippet>
    fun validateOwnership(userId: String, snippetId: UUID)
}