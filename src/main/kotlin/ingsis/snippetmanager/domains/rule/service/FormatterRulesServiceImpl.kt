package ingsis.snippetmanager.domains.rule.service

import ingsis.snippetmanager.domains.rule.dto.FormatterRulesDTO
import ingsis.snippetmanager.domains.rule.model.FormatterRules
import ingsis.snippetmanager.domains.rule.repository.FormatterRulesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FormatterRulesServiceImpl: FormatterRulesService{

    @Autowired
    private var  formatterRulesRepository: FormatterRulesRepository

    constructor(formatterRulesRepository: FormatterRulesRepository){
        this.formatterRulesRepository = formatterRulesRepository
    }
    override fun getFormatterRulesByUserId(userId: String): FormatterRulesDTO {

        try {
            val rules = this.formatterRulesRepository.findByUserId(userId)
            return FormatterRulesDTO(rules)
        }catch (e: Exception){
            return updateFormatterRules(FormatterRulesDTO(0,1,1,1,userId),userId)
        }
    }

    override fun updateFormatterRules(formatterRules: FormatterRulesDTO, userId: String): FormatterRulesDTO {

        try {
            val rules = formatterRulesRepository.findByUserId(userId)
            rules.spaceBeforeColon = formatterRules.spaceBeforeColon
            rules.spaceAfterColon = formatterRules.spaceAfterColon
            rules.spaceBeforeAndAfterAssignationOperator = formatterRules.spaceBeforeAndAfterAssignationOperator
            rules.newLinesBeforePrintLn = formatterRules.newLinesBeforePrintLn
            return FormatterRulesDTO(formatterRulesRepository.save(rules))
        }catch (e: Exception){
            return FormatterRulesDTO(formatterRulesRepository.save(FormatterRules(formatterRules.spaceBeforeColon,formatterRules.spaceAfterColon,formatterRules.spaceBeforeAndAfterAssignationOperator,formatterRules.newLinesBeforePrintLn,userId)))
        }

    }

}