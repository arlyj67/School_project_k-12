package com.schoolproject.school_project.services.implementation

import com.schoolproject.school_project.dto.request.RoomRequestDTO
import com.schoolproject.school_project.dto.response.RoomResponseDTO
import com.schoolproject.school_project.exception_handler.NotFoundException
import com.schoolproject.school_project.exception_handler.RoomAlreadyExistsException
import com.schoolproject.school_project.mapper.toEntity
import com.schoolproject.school_project.mapper.toResponse
import com.schoolproject.school_project.repositories.RoomRepository
import com.schoolproject.school_project.services.ClassroomService
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class ClassroomServiceImpl(
    private val roomRepository: RoomRepository
) : ClassroomService {

    @Transactional
    override fun create(dto: RoomRequestDTO): RoomResponseDTO {
        if (roomRepository.existsByClassroom(dto.classroom)) {
            throw RoomAlreadyExistsException("Room '${dto.classroom}' already exists.")
        }

        val room = dto.toEntity()
        val savedRoom = roomRepository.save(room)
        return savedRoom.toResponse()
    }

    @Transactional
    override fun updateClassroom(id: Long, dto: RoomRequestDTO): RoomResponseDTO {
        val room = roomRepository.findById(id)
            .orElseThrow { NotFoundException("Room '${dto.classroom}' does not exist") }

        room.classroom = dto.classroom
        room.gradeLevel = dto.gradeLevel
        room.capacity = dto.capacity

        return roomRepository.save(room).toResponse()

    }

    @Transactional
    override fun delete(id: Long) {
        val room = roomRepository.findById(id).orElseThrow { NotFoundException("Room '${id}' does not exist") }

        return roomRepository.delete(room)
    }

    @Transactional
    override fun findById(id: Long): RoomResponseDTO {
        val room = roomRepository.findById(id)
            .orElseThrow { NotFoundException("Room with ID '$id' not found") }

        return room.toResponse()
    }

    override fun findAll(): List<RoomResponseDTO> {
        return roomRepository.findAll().map { it.toResponse() }
    }
}