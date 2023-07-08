package ingsis.snippetmanager.domains.test.service

import ingsis.snippetmanager.domains.test.dto.CreateTestDTO
import ingsis.snippetmanager.domains.test.dto.TestDTO
import java.util.*

interface TestService {
    fun createTest(testDTO: CreateTestDTO, userId: String): TestDTO
    fun updateTest(test: TestDTO, userId: String): TestDTO
    fun deleteTest(id: UUID, userId: String)
    fun getTestsByUser(id: String): List<TestDTO>
    fun getTestById(id: UUID, userId: String?): TestDTO
}