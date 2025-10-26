package com.schoolproject.school_project.dto.response

import java.time.DayOfWeek
import java.time.LocalTime

data class TeacherAssignmentResponseDTO (
    val id: Long,
    val teacherId: Long,
    val teacherName: String,
    val subjectId: Long,
    val subjectName: String,
    val roomId: Long,
    val classroom: String,
    val dayOfWeek: DayOfWeek,
    val startTime: LocalTime,
    val endTime: LocalTime,
)
