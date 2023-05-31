package ingsis.snippetmanager.error

import org.springframework.http.HttpStatus

class HTTPError : Exception {

    var status: HttpStatus? = null
    constructor(message: String, status: HttpStatus) : super(message){
        this.status = status
    }
}