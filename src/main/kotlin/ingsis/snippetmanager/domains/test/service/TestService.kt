package ingsis.snippetmanager.domains.test.service

import ingsis.snippetmanager.domains.test.dto.TestDTO
import ingsis.snippetmanager.domains.test.model.Test
import java.util.*

interface TestService {
    fun createTest(testDTO: TestDTO, userId: String): Test
    fun updateTest(test: Test): Test
    fun deleteTest(id: UUID)
    fun getTestsByUser(id: String): List<Test>
}