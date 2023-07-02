package ingsis.snippetmanager.domains.rule.service

import ingsis.snippetmanager.domains.rule.dto.LinterRulesDTO

interface LinterRulesService {

    fun getLinterRulesByUserId(userId: String): LinterRulesDTO
    fun updateLinterRules(linterRules: LinterRulesDTO, userId: String): LinterRulesDTO
}