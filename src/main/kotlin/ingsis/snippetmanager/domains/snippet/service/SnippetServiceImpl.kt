package ingsis.snippetmanager.domains.snippet.service

import ingsis.snippetmanager.domains.rule.model.ComplianceState
import ingsis.snippetmanager.domains.snippet.dto.CreateSnippetDTO
import ingsis.snippetmanager.domains.snippet.dto.SnippetDTO
import ingsis.snippetmanager.domains.snippet.dto.UpdateSnippetDTO
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

    override fun createSnippet(snippet: CreateSnippetDTO, userId: String): SnippetDTO {

        if (!Util.validateField(snippet.name)) throw HTTPError("Invalid snippet name", HttpStatus.BAD_REQUEST)
        if (!Util.validateField(snippet.type)) throw HTTPError("Invalid snippet type", HttpStatus.BAD_REQUEST)

        val s = Snippet(
            snippet.name,
            snippet.type,
            userId,
            snippet.content,)
        return SnippetDTO(this.snippetRepository.save(s))
    }

    override fun updateSnippet(snippet: UpdateSnippetDTO, userId: String): SnippetDTO {
        val snippetToSave = this.snippetRepository.findById(snippet.id!!).get()
        snippetToSave.name = snippet.name
        snippetToSave.type = snippet.type
        snippetToSave.content = snippet.content
        snippetToSave.updatedAt = Date()
        if (snippet.ownerId == userId) return SnippetDTO(this.snippetRepository.save(snippetToSave))
        throw HTTPError("User must own the snippet to edit it", HttpStatus.FORBIDDEN)
    }

    override fun deleteSnippet(id: UUID, userId: String) {
        val snippet = this.snippetRepository.findById(id)
        if (snippet.get().ownerId == userId) return this.snippetRepository.deleteById(id)
        throw HTTPError("User must own the snippet to delete it", HttpStatus.FORBIDDEN)
    }

    override fun getSnippetById(id: UUID, token: String, userId: String): SnippetDTO {
        val snippet = this.snippetRepository.findById(id)
        if (snippet.isPresent) {
            val snippet =  snippet.get()
            if (snippet.ownerId == userId) return SnippetDTO(snippet)
            else{
                val ids = ShareSnippetService.getSharedWithMeSnippetsIds(token);
                if (ids.contains(snippet.id)) return SnippetDTO(snippet)
                else throw HTTPError("User doesn't have access to snippet", HttpStatus.FORBIDDEN)
            }
        } else {
            throw HTTPError("Snippet not found", HttpStatus.NOT_FOUND)
        }
    }

    override fun getSnippetsByUserId(userId: String): List<SnippetDTO> {
        return this.snippetRepository.findByUserId(userId).map { SnippetDTO(it) }
    }

    override fun getAllSnippetsByUserId(userId: String): List<SnippetDTO> {
        return this.snippetRepository.findAllByUserId(userId).map { SnippetDTO(it) }
    }

    override fun getSnippetsByUserIdAndSnippetId(userId: String, snippetId: List<UUID>): List<SnippetDTO> {
        return this.snippetRepository.findAllByUserIdAndSnippetId(userId, snippetId).map { SnippetDTO(it) }
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

    override fun setSnippetCompliance(snippetId: UUID, compliance: ComplianceState) {
        print("setSnippetCompliance with state $compliance")
        val snippet = this.snippetRepository.findById(snippetId)
        if (snippet.isPresent) {
            snippet.get().compliance = compliance
            this.snippetRepository.save(snippet.get())
        } else {
            throw HTTPError("Snippet not found", HttpStatus.NOT_FOUND)
        }
    }
}