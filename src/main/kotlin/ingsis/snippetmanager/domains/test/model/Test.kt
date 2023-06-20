package ingsis.snippetmanager.domains.test.model

import ingsis.snippetmanager.domains.snippet.model.Snippet
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.*

@Entity
@Table(name = "test")
class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    var id: UUID? = null

    @Column(name = "description", nullable = false)
    var description: String? = null

    @CreationTimestamp
    @Column(name = "createdAt", nullable = false, updatable = false)
    var createdAt: Date? = null

    @UpdateTimestamp
    @Column(name = "updatedAt", nullable = true)
    var updatedAt: Date? = null

    @Column(name = "ownerId", nullable = false)
    var ownerId: String? = null

    @Column(name = "input", nullable = false)
    var input: String? = null

    @Column(name = "output", nullable = false)
    var output: String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "snippetId", nullable = false)
    var snippet: Snippet? = null

    constructor() {
        // Default constructor required by JPA
    }

    constructor(
        description: String?,
        ownerId: String?,
        input: String?,
        output: String?,
        snippetId: UUID?
    ) {
        this.description = description
        this.createdAt = Date()
        this.ownerId = ownerId
        this.input = input
        this.output = output
        this.snippet = Snippet().apply { id = snippetId }
    }

    constructor(
        description: String?,
        ownerId: String?,
        input: String?,
        output: String?,
        snippet: Snippet?
    ) {
        this.description = description
        this.createdAt = Date()
        this.ownerId = ownerId
        this.input = input
        this.output = output
        this.snippet = snippet
    }
}