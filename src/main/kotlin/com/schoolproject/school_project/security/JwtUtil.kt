package com.schoolproject.school_project.security

import com.schoolproject.school_project.model.Roles
import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtUtil(
    private val secretKey: SecretKey
) {

    private val expirationMs: Long = 1000 * 60 * 60 * 3

    fun generateToken(userId: Long, username: String, roles: Set<Roles>): String {

        val now = Date()
        val expirationDate = Date(now.time + expirationMs)

        val claims  = mutableMapOf<String, Any?>()
        claims["authorities"] = roles.map { it.name }

        return Jwts.builder()
            .setClaims(claims)
            .setSubject(username)
            .setId(userId.toString())
            .setIssuedAt(now)
            .setExpiration(expirationDate)
            .signWith(secretKey,SignatureAlgorithm.HS256)
            .compact()
    }

    fun generateRefreshToken(userId: Long, username: String, roles: Set<Roles>): String {

        val claims  = mutableMapOf<String, Any?>(
            "userId" to userId,
            "authorities" to roles.map { it.name }
        )

        return Jwts.builder()
            .setClaims(claims)
            .setSubject(username)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() +  1000 * 60 * 60 * 24 * 7))
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact()
    }

    fun extraUsername(token: String): String {
        return Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .body
            .subject
    }

    fun validateToken(token: String): Boolean {
        return try {
            Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
            true
        }catch (ex: JwtException){
            false
        }
    }

    fun extractAllClaims(token: String): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .body
    }
}