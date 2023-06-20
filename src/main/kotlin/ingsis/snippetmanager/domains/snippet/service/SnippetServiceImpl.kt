package ingsis.snippetmanager.domains.snippet.service

import ingsis.snippetmanager.domains.snippet.dto.SnippetDTO
import ingsis.snippetmanager.domains.snippet.model.Snippet
import ingsis.snippetmanager.domains.snippet.repository.SnippetRepository
import ingsis.snippetmanager.error.HTTPError
import ingsis.snippetmanager.service.ShareSnippetService
import ingsis.snippetmanager.util.Util
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.util.*

@Service
class SnippetServiceImpl: SnippetService {

    @Autowired
    private var  snippetRepository: SnippetRepository

    constructor(snippetRepository: SnippetRepository){
        this.snippetRepository = snippetRepository
    }

    override fun createSnippet(snippet: SnippetDTO, userId: String): Snippet {

        if (!Util.validateField(snippet.name)) throw HTTPError("Invalid snippet name", HttpStatus.BAD_REQUEST)
        if (!Util.validateField(snippet.type)) throw HTTPError("Invalid snippet type", HttpStatus.BAD_REQUEST)

        val s = Snippet(
            snippet.name,
            snippet.type,
            userId,
            snippet.content,)
        return this.snippetRepository.save(s)
    }

    override fun updateSnippet(snippet: Snippet, userId: String): Snippet {
        snippet.updatedAt = Date()
        if (snippet.ownerId == userId) return this.snippetRepository.save(snippet)
        throw HTTPError("User must own the snippet to edit it", HttpStatus.FORBIDDEN)
    }

    override fun deleteSnippet(id: UUID, userId: String) {
        val snippet = this.snippetRepository.findById(id)
        if (snippet.get().ownerId == userId) this.snippetRepository.deleteById(id)
        throw HTTPError("User must own the snippet to delete it", HttpStatus.FORBIDDEN)
    }

    override fun getSnippetById(id: UUID, token: String, userId: String): Snippet {
        val snippet = this.snippetRepository.findById(id)
        if (snippet.isPresent) {
            val snippet =  snippet.get()
            if (snippet.ownerId == userId) return snippet
            else{
                val ids = ShareSnippetService.getSharedWithMeSnippetsIds(token);
                if (ids.contains(snippet.id)) return snippet
                else throw HTTPError("User doesn't have access to snippet", HttpStatus.FORBIDDEN)
            }
        } else {
            throw HTTPError("Snippet not found", HttpStatus.NOT_FOUND)
        }
    }

    override fun getSnippetsByUserId(userId: String): List<Snippet> {
        return this.snippetRepository.findByUserId(userId)
    }

    override fun getAllSnippetsByUserId(userId: String): List<Snippet> {
        return this.snippetRepository.findAllByUserId(userId)
    }

    override fun getSnippetsByUserIdAndSnippetId(userId: String, snippetId: List<UUID>): List<Snippet> {
        return this.snippetRepository.findAllByUserIdAndSnippetId(userId, snippetId)
    }

    override fun validateOwnership(userId: String, snippetId: UUID) {
        val snippet = this.snippetRepository.findById(snippetId)
        if (snippet.isPresent) {
            if (snippet.get().ownerId != userId) {
                throw HTTPError("You don't have permission to access this snippet", HttpStatus.UNAUTHORIZED)
            }
        } else {
            throw HTTPError("Snippet not found", HttpStatus.NOT_FOUND)
        }
    }
}