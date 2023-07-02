package ingsis.snippetmanager.domains.rule.repository

import ingsis.snippetmanager.domains.rule.model.FormatterRules
import ingsis.snippetmanager.domains.rule.model.LinterRules
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface LinterRulesRepository: JpaRepository<LinterRules, UUID> {

    @Query("SELECT l FROM LinterRules l WHERE l.userId = :userId")
    fun findByUserId(userId: String): LinterRules
}