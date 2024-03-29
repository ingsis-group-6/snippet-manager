package ingsis.snippetmanager.domains.rule.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "linter_rules")
class LinterRulesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    var id: UUID? = null

    @Column(name = "case_convention", nullable = false)
    open var caseConvention: CaseConvention? = CaseConvention.CAMEL_CASE

    @Column(name = "print_expressions_enabled", nullable = false)
    open var printExpressionsEnabled: Boolean? = false

    @Column(name = "userId", nullable = false)
    open var userId: String? = null

    constructor(
        id: UUID?,
        caseConvention: CaseConvention?,
        printExpressionsEnabled: Boolean?,
        userId: String?
    ) {
        this.id = id
        this.caseConvention = caseConvention
        this.printExpressionsEnabled = printExpressionsEnabled
        this.userId = userId
    }

    constructor(
        caseConvention: CaseConvention?,
        printExpressionsEnabled: Boolean?,
        userId: String?
    ) {
        this.caseConvention = caseConvention
        this.printExpressionsEnabled = printExpressionsEnabled
        this.userId = userId
    }

    constructor(
        userId: String?
    ) {
        this.caseConvention = CaseConvention.CAMEL_CASE
        this.printExpressionsEnabled = false
        this.userId = userId
    }

}

enum class CaseConvention{
    CAMEL_CASE,
    SNAKE_CASE,
}