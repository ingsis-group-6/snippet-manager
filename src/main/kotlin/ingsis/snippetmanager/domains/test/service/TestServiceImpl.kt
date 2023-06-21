package ingsis.snippetmanager.domains.test.service

import ingsis.snippetmanager.domains.snippet.repository.SnippetRepository
import ingsis.snippetmanager.domains.test.dto.TestDTO
import ingsis.snippetmanager.domains.test.model.Test
import ingsis.snippetmanager.domains.test.repository.TestRepository
import ingsis.snippetmanager.error.HTTPError
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

    override fun createTest(testDTO: TestDTO, userId: String): TestDTO {

        val snippet = this.snippetRepository.findById(testDTO.snippetId!!)

        if (snippet.get().ownerId != userId) throw HTTPError("User must own snippet to create a test", HttpStatus.FORBIDDEN)

        val test = Test(
            description = testDTO.description,
            ownerId = userId,
            input = testDTO.input,
            output = testDTO.output,
            snippetId = testDTO.snippetId
        )

        return TestDTO(testRepository.save(test))
    }

    override fun updateTest(test: Test, userId: String): TestDTO {
        if (test.ownerId == userId) return TestDTO(testRepository.save(test))
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
}