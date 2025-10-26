package com.schoolproject.school_project.dto.response

data class AuthTokenResponseDTO(
    val accessToken: String,
    val refreshToken: String
)