package ingsis.snippetmanager.domains.snippet.model

import jakarta.persistence.*
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import java.util.*

@Entity
@Table(name = "snippet")
class Snippet {

    @Id
    @JdbcTypeCode(SqlTypes.UUID)
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    var id: UUID? = null

    @Column(name = "name", nullable = false)
    var name: String? = null

    @Column(name = "type", nullable = false)
    var type: String? = null

    @Column(name = "ownerId", nullable = false)
    var ownerId: String? = null

    @Column(name = "content", nullable = false)
    var content: String? = null //Ahora es content, despues pasa a ser un id para el bucket

    @Column(name = "createdAt", nullable = false)
    var createdAt: Date? = null

    @Column(name = "updatedAt", nullable = true)
    var updatedAt: Date? = null

    constructor(id: UUID?, name: String?, type: String?, ownerId: String?, content: String?, createdAt: Date?, updatedAt: Date?) {
        this.id = id
        this.name = name
        this.type = type
        this.ownerId = ownerId
        this.content = content
        this.createdAt = createdAt
        this.updatedAt = updatedAt
    }

    constructor(name: String?, type: String?, ownerId: String?, content: String?) {
        this.name = name
        this.type = type
        this.ownerId = ownerId
        this.content = content
        this.createdAt = Date()
    }

    constructor() {
    }

    override fun equals(other: Any?): Boolean {

        if (other == null) return false
        if (other !is Snippet) return false
        if (other.id == null) return false
        if (this.id == null) return false
        return this.id == other.id
    }


}