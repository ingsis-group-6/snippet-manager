package ingsis.snippetmanager.domains.rule.repository

import ingsis.snippetmanager.domains.rule.model.FormatterRules
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface FormatterRulesRepository: JpaRepository<FormatterRules,UUID> {

        @Query("SELECT f FROM FormatterRules f WHERE f.userId = :userId")
        fun findByUserId(userId: String): FormatterRules

}