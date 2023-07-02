package ingsis.snippetmanager.domains.rule.model

import ingsis.snippetmanager.domains.rule.dto.FormatterRulesDTO
import jakarta.persistence.*
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import java.util.*

@Entity
@Table(name = "formatter_rules")
class FormatterRules {

    @Id
    @JdbcTypeCode(SqlTypes.UUID)
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    var id: UUID? = null

    @Column(name = "space_before_colon", nullable = false)
    open var spaceBeforeColon: Int? = 0

    @Column(name = "space_after_colon", nullable = false)
    open var spaceAfterColon: Int? = 1

    @Column(name = "space_before_and_after_assignation_operator", nullable = false)
    open var spaceBeforeAndAfterAssignationOperator: Int? = 1

    @Column(name = "new_lines_before_print_ln", nullable = false)
    open var newLinesBeforePrintLn: Int? = 1

    @Column(name = "userId", nullable = false)
    open var userId: String? = null

    constructor(
        id: UUID?,
        spaceBeforeColon: Int?,
        spaceAfterColon: Int?,
        spaceBeforeAndAfterAssignationOperator: Int?,
        newLinesBeforePrintLn: Int?,
        userId: String?
    ) {
        this.id = id
        this.spaceBeforeColon = spaceBeforeColon
        this.spaceAfterColon = spaceAfterColon
        this.spaceBeforeAndAfterAssignationOperator = spaceBeforeAndAfterAssignationOperator
        this.newLinesBeforePrintLn = newLinesBeforePrintLn
        this.userId = userId
    }

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

    constructor(formatterRules: FormatterRulesDTO){
        this.spaceBeforeColon = formatterRules.spaceBeforeColon
        this.spaceAfterColon = formatterRules.spaceAfterColon
        this.spaceBeforeAndAfterAssignationOperator = formatterRules.spaceBeforeAndAfterAssignationOperator
        this.newLinesBeforePrintLn = formatterRules.newLinesBeforePrintLn
        this.userId = formatterRules.userId
    }

}