package com.api.token.config

import io.jsonwebtoken.SignatureAlgorithm

class Constants private constructor(){
    companion object {
        val SIGNATURE = SignatureAlgorithm.HS512
        const val TOKEN_HEADER = "Authorization"
        const val TOKEN_PREFIX = "Bearer "
        const val TOKEN_TYPE = "JWT"
        const val TOKEN_ISSUER = "secure-api"
        const val TOKEN_AUDIENCE = "secure-app"
    }
}