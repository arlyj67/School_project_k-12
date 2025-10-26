package com.schoolproject.school_project.services

import com.schoolproject.school_project.dto.request.RoomRequestDTO
import com.schoolproject.school_project.dto.response.RoomResponseDTO

interface ClassroomService {
    fun create(dto: RoomRequestDTO): RoomResponseDTO
    fun updateClassroom(id: Long, dto: RoomRequestDTO): RoomResponseDTO
    fun delete(id: Long)
    fun findById(id: Long): RoomResponseDTO
    fun findAll(): List<RoomResponseDTO>
}