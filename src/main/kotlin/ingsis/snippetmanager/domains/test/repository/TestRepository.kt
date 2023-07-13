package ingsis.snippetmanager.domains.test.repository

import ingsis.snippetmanager.domains.test.model.Test
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.UUID

interface TestRepository: JpaRepository<Test, UUID> {
    @Query("SELECT t FROM Test t WHERE t.ownerId = :id")
    fun findByOwnerId(@Param("id") id: String): List<Test>
}