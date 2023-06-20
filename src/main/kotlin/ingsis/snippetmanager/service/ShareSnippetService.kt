package ingsis.snippetmanager.service

import io.github.cdimascio.dotenv.Dotenv
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.web.client.RestTemplate
import java.util.*

class ShareSnippetService {

    companion object{

            fun getSharedWithMeSnippetsIds( token: String): List<UUID> {
                val env = Dotenv.load()
                val url = env["SHARE_URI"] + "/shared_with_me/id"
                val template = RestTemplate()
                val headers = HttpHeaders()
                headers.set("Authorization", token)
                val requestEntity = HttpEntity<Void>(headers)
                val response = template.exchange(url,HttpMethod.GET,requestEntity,Array<UUID>::class.java)
                return response.body!!.toList()
            }

            fun deleteSharesFromSnippet(token: String, snippetId: UUID) {
                val env = Dotenv.load()
                val url = env["SHARE_URI"] + "/by_snippet/"+ snippetId
                val template = RestTemplate()
                val headers = HttpHeaders()
                headers.set("Authorization", token)
                val requestEntity = HttpEntity<Void>(headers)
                val response = template.exchange(url,HttpMethod.DELETE,requestEntity,Void::class.java)
            }

    }

}