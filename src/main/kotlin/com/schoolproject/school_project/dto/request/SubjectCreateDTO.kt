package com.schoolproject.school_project.dto.request

import com.schoolproject.school_project.model.GradeLevel

data class SubjectCreateDTO (
    val name : String,
    val gradeLevel : GradeLevel,
)