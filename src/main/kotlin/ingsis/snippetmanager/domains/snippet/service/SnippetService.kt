package ingsis.snippetmanager.domains.snippet.service

import ingsis.snippetmanager.domains.snippet.dto.CreateSnippetDTO
import ingsis.snippetmanager.domains.snippet.dto.SnippetDTO
import ingsis.snippetmanager.domains.snippet.dto.UpdateSnippetDTO
import ingsis.snippetmanager.domains.snippet.model.Snippet
import java.util.*

interface SnippetService {

    fun createSnippet(snippet: CreateSnippetDTO, userId: String): SnippetDTO
    fun updateSnippet(snippet:UpdateSnippetDTO, userId: String): SnippetDTO
    fun deleteSnippet(id: UUID, userId: String)
    fun getSnippetById(id: UUID, token: String, userId: String): SnippetDTO
    fun getSnippetsByUserId(userId: String): List<SnippetDTO>
    fun getAllSnippetsByUserId(userId: String): List<SnippetDTO>
    fun getSnippetsByUserIdAndSnippetId(userId: String, snippets: List<UUID>): List<SnippetDTO>
    fun validateOwnership(userId: String, snippetId: UUID)
}