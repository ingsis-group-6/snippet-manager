package ingsis.snippetmanager.domains.rule.service

import ingsis.snippetmanager.domains.rule.dto.FormatterRulesDTO

interface FormatterRulesService {

    fun getFormatterRulesByUserId(userId: String): FormatterRulesDTO
    fun updateFormatterRules(formatterRules: FormatterRulesDTO, userId: String): FormatterRulesDTO
}