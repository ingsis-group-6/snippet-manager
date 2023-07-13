package ingsis.snippetmanager.domains.rule.dto

import ingsis.snippetmanager.domains.rule.model.FormatterRules

class FormatterRulesDTO {

    var spaceBeforeColon: Int? = 0
    var spaceAfterColon: Int? = 1
    var spaceBeforeAndAfterAssignationOperator: Int? = 1
    var newLinesBeforePrintLn: Int? = 1
    var userId: String? = null

    constructor(
        spaceBeforeColon: Int?,
        spaceAfterColon: Int?,
        spaceBeforeAndAfterAssignationOperator: Int?,
        newLinesBeforePrintLn: Int?,
        userId: String?
    ) {
        this.spaceBeforeColon = spaceBeforeColon
        this.spaceAfterColon = spaceAfterColon
        this.spaceBeforeAndAfterAssignationOperator = spaceBeforeAndAfterAssignationOperator
        this.newLinesBeforePrintLn = newLinesBeforePrintLn
        this.userId = userId
    }

    constructor(rule: FormatterRules){
        this.spaceBeforeColon = rule.spaceBeforeColon
        this.spaceAfterColon = rule.spaceAfterColon
        this.spaceBeforeAndAfterAssignationOperator = rule.spaceBeforeAndAfterAssignationOperator
        this.newLinesBeforePrintLn = rule.newLinesBeforePrintLn
        this.userId = rule.userId
    }

    constructor(userId: String?){
        this.userId = userId
        this.spaceBeforeColon = 0
        this.spaceAfterColon = 1
        this.spaceBeforeAndAfterAssignationOperator = 1
        this.newLinesBeforePrintLn = 1
    }
}