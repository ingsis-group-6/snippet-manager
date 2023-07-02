package ingsis.snippetmanager.domains.rule.dto

import ingsis.snippetmanager.domains.rule.model.CaseConvention
import ingsis.snippetmanager.domains.rule.model.FormatterRules
import ingsis.snippetmanager.domains.rule.model.LinterRules

class LinterRulesDTO {

    var caseConvention: CaseConvention? = null
    var printExpressionsEnabled: Boolean? = false
    var userId: String? = null

    constructor(
        caseConvention: CaseConvention?,
        printExpressionsEnabled: Boolean?,
        userId: String?
    ) {
        this.caseConvention = caseConvention
        this.printExpressionsEnabled = printExpressionsEnabled
        this.userId = userId
    }

    constructor(rule: LinterRules){
        this.caseConvention = rule.caseConvention
        this.printExpressionsEnabled = rule.printExpressionsEnabled
        this.userId = rule.userId
    }

    constructor(userId: String?){
        this.userId = userId
        this.caseConvention = CaseConvention.CAMEL_CASE
        this.printExpressionsEnabled = false
    }
}