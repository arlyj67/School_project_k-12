package com.schoolproject.school_project.services

import com.schoolproject.school_project.dto.request.AssignmentItems
import com.schoolproject.school_project.dto.request.AssignmentUpdateDTO
import com.schoolproject.school_project.dto.request.TeacherAssignmentRequestDTO
import com.schoolproject.school_project.dto.response.TeacherAssignmentResponseDTO

interface TeacherAssignmentService {
    fun assignSubjects(dto: List<TeacherAssignmentRequestDTO>): List<TeacherAssignmentResponseDTO>
    fun getAssignmentsByTeacher(teacherId: Long): List<TeacherAssignmentResponseDTO>
    fun updateAssignment(id: Long, dto: AssignmentUpdateDTO): TeacherAssignmentResponseDTO
    fun deleteAssignment(id: Long)
}