package com.schoolproject.school_project.mapper

import com.schoolproject.school_project.dto.response.TeacherAssignmentResponseDTO
import com.schoolproject.school_project.entities.TeacherAssignment

fun TeacherAssignment.toResponseDTO(): TeacherAssignmentResponseDTO {
    return TeacherAssignmentResponseDTO(
        id = this.id!!,
        teacherId = this.teacher.id!!,
        teacherName = "${teacher.firstName} ${teacher.lastName}",
        subjectId = this.subject.id!!,
        subjectName = this.subject.name,
        roomId = this.room.id!!,
        classroom = this.room.classroom,
        dayOfWeek = this.dayOfWeek,
        startTime = this.startTime,
        endTime = this.endTime,
    )
}