package ingsis.snippetmanager.domains.test.service

import ingsis.snippetmanager.domains.test.dto.TestDTO
import ingsis.snippetmanager.domains.test.model.Test
import java.util.*

interface TestService {
    fun createTest(testDTO: TestDTO, userId: String): TestDTO
    fun updateTest(test: Test, userId: String): TestDTO
    fun deleteTest(id: UUID, userId: String)
    fun getTestsByUser(id: String): List<TestDTO>
}