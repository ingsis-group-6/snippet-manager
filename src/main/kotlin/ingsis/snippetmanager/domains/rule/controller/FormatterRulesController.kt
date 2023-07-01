package ingsis.snippetmanager.domains.rule.controller

import ingsis.snippetmanager.domains.rule.dto.FormatterRulesDTO
import ingsis.snippetmanager.domains.rule.service.FormatterRulesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@CrossOrigin("*")
class FormatterRulesController{

    @Autowired
    private var ruleService: FormatterRulesService

    constructor(ruleService: FormatterRulesService){
        this.ruleService = ruleService
    }

    @GetMapping("/rule/formatter")
    fun getFormatterRulesByUserId(principal: Principal): ResponseEntity<FormatterRulesDTO>{
        return ResponseEntity.ok(ruleService.getFormatterRulesByUserId(principal.name))
    }

    @PutMapping("/rule/formatter")
    fun updateFormatterRules(formatterRules: FormatterRulesDTO, principal: Principal): ResponseEntity<FormatterRulesDTO>{
        return ResponseEntity.ok(ruleService.updateFormatterRules(formatterRules, principal.name))
    }
}