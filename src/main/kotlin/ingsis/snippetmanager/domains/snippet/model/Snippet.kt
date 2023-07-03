package ingsis.snippetmanager.domains.snippet.model

import ingsis.snippetmanager.domains.test.model.Test
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "snippet")
class Snippet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    var id: UUID? = null

    @Column(name = "name", nullable = false)
    var name: String? = null

    @Column(name = "type", nullable = false)
    var type: String? = null

    @Column(name = "ownerId", nullable = false)
    var ownerId: String? = null

    @Column(name = "content", nullable = false)
    var content: String? = null //Ahora es content, después pasa a ser un id para el bucket

    @Column(name = "createdAt", nullable = false)
    var createdAt: Date? = null

    @Column(name = "updatedAt", nullable = true)
    var updatedAt: Date? = null

    @OneToMany(mappedBy = "snippet", cascade = [CascadeType.ALL], orphanRemoval = true)
    var tests: MutableList<Test> = mutableListOf()

    @Column(name = "status", nullable = false)
    var status: LintingStatus? = LintingStatus.PENDING

    constructor(
        id: UUID?,
        name: String?,
        type: String?,
        ownerId: String?,
        content: String?,
        createdAt: Date?,
        updatedAt: Date?,
        status: LintingStatus?
    ) {
        this.id = id
        this.name = name
        this.type = type
        this.ownerId = ownerId
        this.content = content
        this.createdAt = createdAt
        this.updatedAt = updatedAt
        this.status = status
    }

    constructor(name: String?, type: String?, ownerId: String?, content: String?, status: LintingStatus?) {
        this.name = name
        this.type = type
        this.ownerId = ownerId
        this.content = content
        this.createdAt = Date()
        this.tests = mutableListOf()
        this.status = status
    }

    constructor(name: String?, type: String?, ownerId: String?, content: String?, tests: MutableList<Test>) {
        this.name = name
        this.type = type
        this.ownerId = ownerId
        this.content = content
        this.createdAt = Date()
        this.tests = tests
    }

    constructor(name: String?, type: String?, ownerId: String?, content: String?) {
        this.name = name
        this.type = type
        this.ownerId = ownerId
        this.content = content
        this.createdAt = Date()
        this.tests = mutableListOf()
        this.status = LintingStatus.PENDING
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

enum class LintingStatus {
    PENDING, COMPLIANT, NON_COMPLIANT
}