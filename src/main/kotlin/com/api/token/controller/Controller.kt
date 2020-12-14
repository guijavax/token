package com.api.token.controller

import com.api.token.createtoken.GenerateToken
import com.api.token.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.security.sasl.AuthenticationException

@RestController
@RequestMapping("/api")
class Controller {

    @Autowired
    lateinit var generateToken: GenerateToken

    @PostMapping("/token")
    fun createToken(@RequestBody user: User, @RequestHeader("key") key : String) : ResponseEntity<*>{
       return try {
            val configs = mapOf<String,Any>("key" to key, "user" to user)
            val jsonResponse = mapOf<String, String>("token" to generateToken.generateToken(configs))
            ResponseEntity.ok().body(jsonResponse)
        } catch (e : AuthenticationException) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.message)
        }
    }
}