package ingsis.snippetmanager.domains.test.service

import ingsis.snippetmanager.domains.snippet.repository.SnippetRepository
import ingsis.snippetmanager.domains.test.dto.CreateTestDTO
import ingsis.snippetmanager.domains.test.dto.TestDTO
import ingsis.snippetmanager.domains.test.model.Test
import ingsis.snippetmanager.domains.test.repository.TestRepository
import ingsis.snippetmanager.error.HTTPError
import ingsis.snippetmanager.service.ShareSnippetService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.util.*

@Service
class TestServiceImpl : TestService{

    @Autowired
    private var testRepository: TestRepository

    @Autowired
    private var snippetRepository: SnippetRepository

    constructor(testRepository: TestRepository, snippetRepository: SnippetRepository) {
        this.testRepository = testRepository
        this.snippetRepository = snippetRepository
    }

    override fun createTest(token: String, testDTO: CreateTestDTO, userId: String): TestDTO {

        val snippet = this.snippetRepository.findById(testDTO.snippetId!!)
        val sharedIds = ShareSnippetService.getSharedWithMeSnippetsIds(token)
        if (snippet.get().ownerId != userId && !sharedIds.contains(testDTO.snippetId)) throw HTTPError("User must have access to snippet to create a test", HttpStatus.FORBIDDEN)

        val test = Test(
            description = testDTO.description,
            ownerId = userId,
            input = testDTO.input.toString(),
            output = testDTO.output.toString(),
            snippet = snippet.get()
        )

        return TestDTO(testRepository.save(test))
    }

    override fun updateTest(test: TestDTO, userId: String): TestDTO {
        if (test.ownerId == userId) return TestDTO(testRepository.save(Test(test)))
        throw HTTPError("User must own test to edit it", HttpStatus.FORBIDDEN)
    }

    override fun deleteTest(id: UUID,userId: String) {
        val test = this.testRepository.findById(id)
        if (test.get().ownerId == userId) return testRepository.deleteById(id)
        throw HTTPError("User must own test to edit it", HttpStatus.FORBIDDEN)
    }

    override fun getTestsByUser(id: String): List<TestDTO> {
        return testRepository.findByOwnerId(id).map { TestDTO(it) }
    }

    override fun getTestById(id: UUID, userId: String?): TestDTO {
        val test = testRepository.findById(id)
        if (test.get().ownerId == userId) return TestDTO(test.get())
        throw HTTPError("User must own test to edit it", HttpStatus.FORBIDDEN)
    }
}