package com.schoolproject.school_project.mapper

import com.schoolproject.school_project.dto.request.RoomRequestDTO
import com.schoolproject.school_project.dto.response.RoomResponseDTO
import com.schoolproject.school_project.entities.Room

fun RoomRequestDTO.toEntity(): Room {
    return Room(
        classroom = this.classroom,
        gradeLevel = this.gradeLevel,
        capacity = this.capacity,
    )
}

fun Room.toResponse(): RoomResponseDTO {
    return RoomResponseDTO(
        id = this.id!!,
        classroom = this.classroom,
        gradeLevel = this.gradeLevel,
        capacity = this.capacity,
    )
}