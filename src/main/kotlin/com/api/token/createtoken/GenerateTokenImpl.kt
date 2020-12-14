package com.api.token.createtoken

import com.api.token.createtoken.config.Constants
import com.api.token.model.User
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets
import java.util.*
import javax.crypto.spec.SecretKeySpec
import javax.security.sasl.AuthenticationException
import kotlin.collections.HashMap


@Service
class GenerateTokenImpl : GenerateToken {

    @Value("\${secret}")
    val secret : String = ""

    private var configs : Map<String, Any> = HashMap<String, Any>()

    override fun generateToken(configs: Map<String, Any>) : String{
        this.configs = configs
        if(getMapElement("key")?.toString() != secret) throw AuthenticationException("N\u00e3o Autorizado")
        val user : User = getMapElement("user") as User
        return Jwts.builder()
                .signWith(SecretKeySpec(Base64.getDecoder().decode(secret.toByteArray(StandardCharsets.UTF_8)),Constants.SIGNATURE.jcaName))
                .setHeaderParam("typ", Constants.TOKEN_TYPE)
                .setIssuer(Constants.TOKEN_ISSUER)
                .setAudience(Constants.TOKEN_AUDIENCE)
                .setSubject(user.username)
                .setExpiration(Date(System.currentTimeMillis() + 864000000))
                .claim("email", user.email)
                .compact()
    }

    private fun getMapElement(key : String) : Any? = this.configs[key]

}