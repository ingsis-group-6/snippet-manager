package ingsis.snippetmanager.domains.test.model

import ingsis.snippetmanager.domains.snippet.model.Snippet
import jakarta.persistence.*
import org.hibernate.type.SqlTypes
import org.hibernate.annotations.JdbcTypeCode
import java.util.*

@Entity
@Table(name = "test")
class Test {

    @Id
    @JdbcTypeCode(SqlTypes.UUID)
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    var id: UUID? = null

    @Column(name = "description", nullable = false)
    var description: String? = null

    @Column(name = "createdAt", nullable = false)
    var createdAt: Date? = null

    @Column(name = "updatedAt", nullable = true)
    var updatedAt: Date? = null

    @Column(name = "ownerId", nullable = false)
    var ownerId: String? = null

    @Column(name = "input", nullable = false) //TODO: multiple inputs
    var input: String? = null

    @Column(name = "output", nullable = false) //TODO: multiple outputs
    var output: String? = null

    @ManyToOne
    @JoinColumn(name = "snippetId", nullable = false)
    var snippet: Snippet? = null
    constructor(id: UUID?, description: String?, createdAt: Date?, updatedAt: Date?, ownerId: String?, input: String?, output: String?, snippet: Snippet?) {
        this.id = id
        this.description = description
        this.createdAt = createdAt
        this.updatedAt = updatedAt
        this.ownerId = ownerId
        this.input = input
        this.output = output
    }

    constructor(description: String?, ownerId: String?, input: String?, output: String?, snippetId: UUID?) {
        this.description = description
        this.createdAt = Date()
        this.ownerId = ownerId
        this.input = input
        this.output = output
        this.snippet = Snippet().apply { id = snippetId }
    }

    constructor(description: String?, ownerId: String?, input: String?, output: String?, snippet: Snippet?){
        this.description = description
        this.createdAt = Date()
        this.ownerId = ownerId
        this.input = input
        this.output = output
        this.snippet = snippet
    }

}