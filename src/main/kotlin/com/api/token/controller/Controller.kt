package com.api.token.controller

import com.api.token.CallResponse
import com.api.token.Response
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

    private var response = CallResponse()

    @PostMapping("/token")
    fun createToken(@RequestBody user: User, @RequestHeader("key") key : String) : ResponseEntity<*>{
       return try {
            val configs = mapOf("key" to key, "user" to user)
            val jsonResponse = mapOf("token" to generateToken.generateToken(configs))
            response.sendResponse(Response.ResponseSuccess(ResponseEntity.ok(jsonResponse)))
        } catch (e : AuthenticationException) {
            response.sendResponse(Response.ResponseError(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.message)))
        }
    }
}