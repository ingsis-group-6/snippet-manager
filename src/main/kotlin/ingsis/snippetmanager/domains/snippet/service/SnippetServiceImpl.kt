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

    override fun updateSnippet(snippet: Snippet): Snippet {
        snippet.updatedAt = Date()
        return this.snippetRepository.save(snippet)
    }

    override fun deleteSnippet(id: UUID, token: String) {
        this.snippetRepository.deleteById(id)
    }

    override fun getSnippetById(id: UUID): Snippet {
        val snippet = this.snippetRepository.findById(id)
        if (snippet.isPresent) {
            return snippet.get()
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