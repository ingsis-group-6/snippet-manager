package ingsis.snippetmanager.domains.test.service

import ingsis.snippetmanager.domains.snippet.repository.SnippetRepository
import ingsis.snippetmanager.domains.test.dto.TestDTO
import ingsis.snippetmanager.domains.test.model.Test
import ingsis.snippetmanager.domains.test.repository.TestRepository
import org.springframework.beans.factory.annotation.Autowired
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

    override fun createTest(testDTO: TestDTO, userId: String): Test {

        val test = Test(
            description = testDTO.description,
            ownerId = userId,
            input = testDTO.input,
            output = testDTO.output,
            snippetId = testDTO.snippetId
        )

        return testRepository.save(test)
    }

    override fun updateTest(test: Test): Test {
        return testRepository.save(test)
    }

    override fun deleteTest(id: UUID) {
        testRepository.deleteById(id)
    }

    override fun getTestsByUser(id: String): List<Test> {
        return testRepository.findByOwnerId(id)
    }
}