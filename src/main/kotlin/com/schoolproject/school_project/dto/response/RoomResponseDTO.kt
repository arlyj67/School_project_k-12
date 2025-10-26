package com.schoolproject.school_project.dto.response

import com.schoolproject.school_project.model.GradeLevel

data class RoomResponseDTO (
    val id : Long,
    val classroom : String,
    val gradeLevel : GradeLevel,
    val capacity : Int,
)