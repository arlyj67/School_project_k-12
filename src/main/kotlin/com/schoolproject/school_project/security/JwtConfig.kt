package com.schoolproject.school_project.security

import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.crypto.SecretKey
import java.util.Base64

@Configuration
class JwtConfig {

    @Value("\${jwt.secret}")
    private lateinit var secretKey: String

    @Bean
    fun jwtSecretKey(): SecretKey{
        val decodeKey = Base64.getDecoder().decode(secretKey)
        return Keys.hmacShaKeyFor(decodeKey) as SecretKey
    }

}