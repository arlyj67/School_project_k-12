package com.schoolproject.school_project.dto.response

data class AuthAdminResponseDTO (
    val adminID: String,
    val fullName: String,
    val email: String,
    val roles: Set<String>,
    val rawPassword: String
)