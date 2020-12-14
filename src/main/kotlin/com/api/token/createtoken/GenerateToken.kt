package com.api.token.createtoken

interface GenerateToken {
    fun generateToken(configs : Map<String, Any>) : String
}