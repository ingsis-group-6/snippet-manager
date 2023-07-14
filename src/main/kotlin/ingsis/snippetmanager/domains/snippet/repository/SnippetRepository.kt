package ingsis.snippetmanager.domains.snippet.repository

import ingsis.snippetmanager.domains.snippet.model.Snippet
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface SnippetRepository : JpaRepository<Snippet, UUID> {

    @Query("SELECT s FROM Snippet s WHERE s.ownerId = :userId")
    fun findByUserId(userId: String): List<Snippet>

    @Query("SELECT s FROM Snippet s WHERE s.ownerId = :userId")
    fun findAllByUserId(userId: String): List<Snippet>

    @Query("SELECT s FROM Snippet s WHERE s.ownerId = :userId OR s.id IN :snippets")
    fun findAllByUserIdAndSnippetId(userId: String, snippets: List<UUID>): List<Snippet>

    @Query("SELECT s FROM Snippet s WHERE s.id IN :snippets")
    fun findAllInIdList(snippets: List<UUID>): List<Snippet>
}