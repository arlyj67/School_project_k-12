package com.schoolproject.school_project.dto.request

import com.schoolproject.school_project.model.GradeLevel

data class RoomRequestDTO(
    val classroom: String,
    val gradeLevel: GradeLevel,
    val capacity: Int
)

//data class RoomUpdateDTO(
//    val classroom: String,
//    val gradeLevel: GradeLevel,
//    val capacity: Int,
//    val teacherId: Set<Long> = emptySet()
//)