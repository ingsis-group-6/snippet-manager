package ingsis.snippetmanager.domains.rule.repository

import ingsis.snippetmanager.domains.rule.model.FormatterRules
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface FormatterRulesRepository: JpaRepository<FormatterRules,UUID> {

        @Query("SELECT f FROM FormatterRules f WHERE f.userId = :userId")
        fun findByUserId(userId: String): FormatterRules

        @Query("SELECT f FROM FormatterRules f WHERE f.userId = :userId")
        fun findAllByUserId(userId: String): FormatterRules

        @Query("SELECT f FROM FormatterRules f WHERE f.userId = :userId OR f.id IN :formatterRules")
        fun findAllByUserIdAndFormatterRulesId(userId: String, formatterRules: List<UUID>): FormatterRules

}