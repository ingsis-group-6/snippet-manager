package ingsis.snippetmanager.domains.snippet.dto

class SnippetDTO {

    var name: String? = null
    var type: String? = null
    var content: String? = null

    constructor(name: String?, type: String?, content: String?) {
        this.name = name
        this.type = type
        this.content = content
    }

}