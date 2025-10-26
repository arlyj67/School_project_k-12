package com.schoolproject.school_project.dto.response

import com.schoolproject.school_project.model.Roles
import java.time.LocalDate
import java.time.LocalDateTime

data class AdministrationResponseDTO (
    val adminID: String,
    val firstName: String,
    val lastName: String,
    val middleName: String?,
    val birthday: String,
    val gender: String,
    val contactNumber: String,
    val roles: Set<Roles>,
    val hireDate: LocalDate,
    val email: String,
    val createDate: LocalDateTime,
    val updateDate: LocalDateTime,
)