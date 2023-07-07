package ingsis.snippetmanager.domains.rule.repository

import ingsis.snippetmanager.domains.rule.model.LinterRulesEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface LinterRulesRepository: JpaRepository<LinterRulesEntity, UUID> {

    @Query("SELECT l FROM LinterRulesEntity l WHERE l.userId = :userId")
    fun findByUserId(userId: String): LinterRulesEntity
}