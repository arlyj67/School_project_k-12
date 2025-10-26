package com.schoolproject.school_project.dto.response

import com.schoolproject.school_project.model.GradeLevel

data class SubjectResponseDTO (
    val id : Long,
    val name : String,
    val gradeLevel : GradeLevel,
)