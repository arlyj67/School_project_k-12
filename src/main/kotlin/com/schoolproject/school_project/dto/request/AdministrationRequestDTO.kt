package com.schoolproject.school_project.dto.request

import com.schoolproject.school_project.model.Roles
import java.time.LocalDate

data class AdministrationRequestDTO (
    val firstName: String,
    val lastName: String,
    val middleName: String?,
    val birthday: String,
    val gender: String,
    val contactNumber: String,
    val roles: Set<Roles>,
    val hireDate: LocalDate,
)

data class AdministrationUpdateDTO(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val middleName: String?,
    val birthday: String,
    val gender: String,
    val contactNumber: String,
    val position: Roles,
    val roles: Set<Roles>,
)