package com.schoolproject.school_project.dto.request

import java.time.DayOfWeek
import java.time.LocalTime

data class TeacherAssignmentRequestDTO(
    val teacherId: Long,
    val assignments: List<AssignmentItems>
)

data class AssignmentItems (
    val roomId : Long,
    val subjectId : Long,
    val dayOfWeek : DayOfWeek,
    val startTime : LocalTime,
    val endTime : LocalTime,
)

data class AssignmentUpdateDTO(
    val teacherId: Long,
    val roomId: Long,
    val subjectId: Long,
    val dayOfWeek: DayOfWeek,
    val startTime: LocalTime,
    val endTime: LocalTime,
)