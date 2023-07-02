package ingsis.snippetmanager.domains.rule.controller

import ingsis.snippetmanager.domains.rule.dto.FormatterRulesDTO
import ingsis.snippetmanager.domains.rule.dto.LinterRulesDTO
import ingsis.snippetmanager.domains.rule.model.LinterRules
import ingsis.snippetmanager.domains.rule.service.FormatterRulesService
import ingsis.snippetmanager.domains.rule.service.LinterRulesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@CrossOrigin("*")
class LinterRulesController {

    @Autowired
    private var ruleService: LinterRulesService

    constructor(ruleService: LinterRulesService){
        this.ruleService = ruleService
    }

    @GetMapping("/rule/linter")
    fun getFormatterRulesByUserId(principal: Principal): ResponseEntity<LinterRulesDTO> {
        return ResponseEntity.ok(ruleService.getLinterRulesByUserId(principal.name))
    }

    @PutMapping("/rule/linter")
    fun updateFormatterRules(@RequestBody linterRules: LinterRulesDTO, principal: Principal): ResponseEntity<LinterRulesDTO> {
        return ResponseEntity.ok(ruleService.updateLinterRules(linterRules, principal.name))
    }

}