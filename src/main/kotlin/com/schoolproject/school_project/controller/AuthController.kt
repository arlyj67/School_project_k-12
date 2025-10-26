package com.schoolproject.school_project.controller

import com.schoolproject.school_project.dto.request.AuthRequestDTO
import com.schoolproject.school_project.dto.response.AuthTokenResponseDTO
import com.schoolproject.school_project.repositories.UserRepository
import com.schoolproject.school_project.security.JwtUtil
import org.springframework.http.ResponseEntity
import org.springframework.security.core.AuthenticationException
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/api/auth")
class AuthController(
    private val jwtUtil: JwtUtil,
    private val userRepository: UserRepository
) {


    @PostMapping("/login")
    fun login(@RequestBody request: AuthRequestDTO): ResponseEntity<AuthTokenResponseDTO> {

        return try {
            val user = userRepository.findByUsername(request.username)
                .orElseThrow { RuntimeException("User not found") }

            val accessToken = jwtUtil.generateToken(
                userId = user.id!!,
                username = user.username!!,
                roles = user.roles
            )

            val refreshToken = jwtUtil.generateRefreshToken(
                userId = user.id!!,
                username = user.username!!,
                roles = user.roles
            )

            ResponseEntity.ok(AuthTokenResponseDTO(accessToken, refreshToken))

        } catch (ex: AuthenticationException) {
            println("Authentication failed: ${ex.message}")
            ResponseEntity.status(401).body(AuthTokenResponseDTO("Authentication failed: ${ex.message}", ""))
        }
    }

    @PostMapping("/refresh")
    fun refreshToken(@RequestBody request: Map<String, String>): ResponseEntity<AuthTokenResponseDTO> {

        val refreshToken = request["refreshToken"]?: return ResponseEntity.badRequest().body(null)

        if (!jwtUtil.validateToken(refreshToken)) {
            return ResponseEntity.status(401).body(null)
        }

        val username = jwtUtil.extraUsername(refreshToken)

        val user = userRepository.findByUsername(username)
            .orElseThrow { RuntimeException("User not found") }

        val newAccessToken = jwtUtil.generateToken(
            userId = user.id!!,
            username = user.username!!,
            roles = user.roles
        )

        return ResponseEntity.ok(AuthTokenResponseDTO(
            accessToken = newAccessToken,
            refreshToken = refreshToken
        ))

    }
}