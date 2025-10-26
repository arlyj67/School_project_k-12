package com.schoolproject.school_project.services

import com.schoolproject.school_project.dto.request.StudentRequestDTO
import com.schoolproject.school_project.dto.request.StudentUpdateDTO
import com.schoolproject.school_project.dto.response.StudentResponseDTO

interface StudentService {
    fun register (dto: StudentRequestDTO): StudentResponseDTO
    fun update(id: Long, dto: StudentUpdateDTO): StudentResponseDTO
    fun delete(id: Long)
    fun findAll(): List<StudentResponseDTO>
    fun getAll(): List<StudentResponseDTO>
    fun getById(id: Long): StudentResponseDTO?
    fun findByStatus(status: String): List<StudentResponseDTO>
    fun findByRefNumber(refNumber: String): StudentResponseDTO
}