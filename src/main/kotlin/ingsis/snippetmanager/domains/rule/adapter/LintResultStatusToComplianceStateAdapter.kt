package ingsis.snippetmanager.domains.rule.adapter

import ingsis.snippetmanager.domains.rule.model.ComplianceState
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import snippet.events.lint.LintResultStatus

@Component
class LintResultStatusToComplianceStateAdapter {
    fun toComplianceState(lintResultStatus: LintResultStatus): ComplianceState {
        return when (lintResultStatus) {
            LintResultStatus.COMPLIANT -> ComplianceState.COMPLIANT
            LintResultStatus.NON_COMPLIANT -> ComplianceState.NON_COMPLIANT
            LintResultStatus.PENDING -> ComplianceState.PENDING
        }
    }
}