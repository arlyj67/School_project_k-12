package com.schoolproject.school_project.services

import com.schoolproject.school_project.dto.request.SubjectCreateDTO
import com.schoolproject.school_project.dto.response.SubjectResponseDTO
import com.schoolproject.school_project.model.GradeLevel
import com.schoolproject.school_project.exception_handler.responses.ApiResponse
import org.springframework.http.ResponseEntity
import javax.security.auth.Subject

interface SubjectService {
    fun create(dto: SubjectCreateDTO): SubjectResponseDTO
    fun findAll(): List<SubjectResponseDTO>
    fun findByGradeLevel(gradeLevel: GradeLevel): List<SubjectResponseDTO>
}