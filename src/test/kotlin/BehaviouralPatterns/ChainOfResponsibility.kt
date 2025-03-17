package BehaviouralPatterns

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

interface HandlerChain{
    fun addHeader(inputHeader: String): String
}

class AuthenticartionHeader (val token: String?, var next: HandlerChain? = null ): HandlerChain{
    override fun addHeader(inputHeader: String) =
        "$inputHeader\nAuthorization: $token"
            .let{ next?.addHeader(it) ?: it }
}
class ContentTypeHeader( val contentType: String, var next: HandlerChain? = null): HandlerChain{
    override fun addHeader(inputHeader: String): String =
        "$inputHeader\nContentType: $contentType"
            .let{ next?.addHeader(it) ?: it }
}

class BodyPayLoadHeader( val body: String, var next: HandlerChain? = null): HandlerChain{
    override fun addHeader(inputHeader: String): String =
        ("$inputHeader\n" + "$body")
            .let{ next?.addHeader(it) ?: it }
}

class ChainOfResponsibilityTest(){
    @Test
    fun testChainOfResponsibility (){
        val authenticartionHeader = AuthenticartionHeader("123456")
        val contentTypeHeader = ContentTypeHeader ("json")
        val bodyPayLoadHeader = BodyPayLoadHeader("Body: {\"username\" = \"john\"}")

        authenticartionHeader.next = contentTypeHeader
        contentTypeHeader.next = bodyPayLoadHeader

        val messageWithAuthentication = authenticartionHeader.addHeader("Headers with authentication")
        println(messageWithAuthentication)
        println("\n")
        val messageWithOutAuthentication = contentTypeHeader.addHeader("Headers without authentication")
        println(messageWithOutAuthentication)

        Assertions.assertThat(messageWithAuthentication).isEqualTo(
            """
                Headers with authentication
                Authorization: 123456
                ContentType: json
                Body: {"username" = "john"}
            """.trimIndent()
        )
    }
}