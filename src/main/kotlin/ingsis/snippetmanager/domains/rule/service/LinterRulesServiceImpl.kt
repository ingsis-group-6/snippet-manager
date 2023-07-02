package ingsis.snippetmanager.domains.rule.service

import ingsis.snippetmanager.domains.rule.dto.FormatterRulesDTO
import ingsis.snippetmanager.domains.rule.dto.LinterRulesDTO
import ingsis.snippetmanager.domains.rule.model.CaseConvention
import ingsis.snippetmanager.domains.rule.model.FormatterRules
import ingsis.snippetmanager.domains.rule.model.LinterRules
import ingsis.snippetmanager.domains.rule.repository.FormatterRulesRepository
import ingsis.snippetmanager.domains.rule.repository.LinterRulesRepository
import ingsis.snippetmanager.domains.rule.service.LinterRulesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LinterRulesServiceImpl: LinterRulesService {

    @Autowired
    private var  linterRulesRepository: LinterRulesRepository

    constructor(linterRulesRepository: LinterRulesRepository){
        this.linterRulesRepository = linterRulesRepository
    }

    override fun getLinterRulesByUserId(userId: String): LinterRulesDTO {
        try {
            val rules = this.linterRulesRepository.findByUserId(userId)
            return LinterRulesDTO(rules)
        }catch (e: Exception){
            return updateLinterRules(LinterRulesDTO(userId),userId)
        }
    }

    override fun updateLinterRules(linterRules: LinterRulesDTO, userId: String): LinterRulesDTO {
        try {
            val rules = linterRulesRepository.findByUserId(userId)
            rules.caseConvention = linterRules.caseConvention as CaseConvention
            rules.printExpressionsEnabled = linterRules.printExpressionsEnabled
            return LinterRulesDTO(linterRulesRepository.save(rules))
        }catch (e: Exception){
            return LinterRulesDTO(linterRulesRepository.save(LinterRules(linterRules.caseConvention as CaseConvention,linterRules.printExpressionsEnabled,userId)))
        }
    }
}