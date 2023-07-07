package ingsis.snippetmanager.domains.rule.service

import ingsis.snippetmanager.domains.rule.dto.LinterRulesDTO
import ingsis.snippetmanager.domains.rule.model.CaseConvention
import ingsis.snippetmanager.domains.rule.model.LinterRulesEntity

import ingsis.snippetmanager.domains.rule.repository.LinterRulesRepository
import ingsis.snippetmanager.domains.snippet.service.SnippetService
import ingsis.snippetmanager.redis.producer.LintRequestProducer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import snippet.events.lint.LintRequestEvent
import snippet.events.lint.rules.LinterRules

@Service
class LinterRulesServiceImpl: LinterRulesService {

    @Autowired
    private var  linterRulesRepository: LinterRulesRepository

    @Autowired
    private var snippetService: SnippetService

    @Autowired
    private var producer: LintRequestProducer

    constructor(linterRulesRepository: LinterRulesRepository, snippetService: SnippetService, producer: LintRequestProducer){
        this.linterRulesRepository = linterRulesRepository
        this.snippetService = snippetService
        this.producer = producer
    }

    override suspend fun getLinterRulesByUserId(userId: String): LinterRulesDTO {
        try {
            val rules = this.linterRulesRepository.findByUserId(userId)
            return LinterRulesDTO(rules)
        }catch (e: Exception){
            return updateLinterRules(LinterRulesDTO(userId),userId)
        }
    }

    override suspend fun updateLinterRules(linterRules: LinterRulesDTO, userId: String): LinterRulesDTO {
        try {
            val rules = linterRulesRepository.findByUserId(userId)
            rules.caseConvention = linterRules.caseConvention as CaseConvention
            rules.printExpressionsEnabled = linterRules.printExpressionsEnabled
            sendSnippetsToLinterService(userId, linterRules)
            return LinterRulesDTO(linterRulesRepository.save(rules))
        }catch (e: Exception){
            val createdLinterRules = linterRulesRepository.save(LinterRulesEntity(linterRules.caseConvention as CaseConvention,linterRules.printExpressionsEnabled,userId))
            sendSnippetsToLinterService(userId, linterRules)
            return LinterRulesDTO(createdLinterRules)
        }
    }

    private suspend fun sendSnippetsToLinterService(userId: String, linterRulesDto: LinterRulesDTO) {

        val userSnippets = this.snippetService.getSnippetsByUserId(userId)
        for (snippet in userSnippets) {
            val rules = LinterRules(enumValueOf<snippet.events.lint.rules.CaseConvention>(linterRulesDto.caseConvention.toString()), linterRulesDto.printExpressionsEnabled!!)
            val event = LintRequestEvent(snippet.id!!, snippet.content!!, rules)
            this.producer.publishEvent(event)

        }




    }
}