package ingsis.snippetmanager.domains.rule.service

import ingsis.snippetmanager.domains.rule.dto.LinterRulesDTO

interface LinterRulesService {

    suspend fun getLinterRulesByUserId(userId: String): LinterRulesDTO
    suspend fun updateLinterRules(linterRules: LinterRulesDTO, userId: String): LinterRulesDTO
}