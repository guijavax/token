package com.api.token

import org.springframework.http.ResponseEntity

sealed class Response(open val responseEntity: ResponseEntity<*>){
    data class ResponseSuccess(override val responseEntity: ResponseEntity<*>) : Response(responseEntity)
    data class ResponseError(override val responseEntity: ResponseEntity<*>) : Response(responseEntity)
}

class CallResponse(response: Response) {
    private val response = response
    fun sendResponse() : ResponseEntity<*> = response.responseEntity
}
