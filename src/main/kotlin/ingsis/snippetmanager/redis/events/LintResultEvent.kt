package ingsis.snippetmanager.redis.events

import java.util.UUID

data class LintResultEvent(
    val lintedSnippetId: String,
    val status: String
)
