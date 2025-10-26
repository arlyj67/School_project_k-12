package com.schoolproject.school_project.mapper

import com.schoolproject.school_project.dto.response.SubjectResponseDTO
import com.schoolproject.school_project.entities.Subject

fun Subject.toResponseDTO(): SubjectResponseDTO {
    return SubjectResponseDTO(
        id = this.id!!,
        name = this.name,
        gradeLevel = this.gradeLevel
    )
}