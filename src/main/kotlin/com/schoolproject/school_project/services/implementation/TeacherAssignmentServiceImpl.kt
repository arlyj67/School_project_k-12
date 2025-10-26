package com.schoolproject.school_project.services.implementation

import com.schoolproject.school_project.dto.request.AssignmentUpdateDTO
import com.schoolproject.school_project.dto.request.TeacherAssignmentRequestDTO
import com.schoolproject.school_project.dto.response.TeacherAssignmentResponseDTO
import com.schoolproject.school_project.entities.TeacherAssignment
import com.schoolproject.school_project.exception_handler.NotFoundException
import com.schoolproject.school_project.exception_handler.ResourceNotFoundException
import com.schoolproject.school_project.exception_handler.ScheduleConflictException
import com.schoolproject.school_project.mapper.toResponseDTO
import com.schoolproject.school_project.repositories.AdministrationRepository
import com.schoolproject.school_project.repositories.RoomRepository
import com.schoolproject.school_project.repositories.SubjectRepository
import com.schoolproject.school_project.repositories.TeacherAssignmentRepository
import com.schoolproject.school_project.services.TeacherAssignmentService
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class TeacherAssignmentServiceImpl(
    private val assignmentRepository: TeacherAssignmentRepository,
    private val teacherRepository: AdministrationRepository,
    private val roomRepository: RoomRepository,
    private val subjectRepository: SubjectRepository
) : TeacherAssignmentService {

    override fun assignSubjects(dto: List<TeacherAssignmentRequestDTO>): List<TeacherAssignmentResponseDTO> {
        return dto.flatMap { request ->
            val teacher = teacherRepository.findById(request.teacherId)
                .orElseThrow { ResourceNotFoundException("Teacher $request.teacherId not found}") }

            val assignments = request.assignments.map { item ->
                val subject = subjectRepository.findById(item.subjectId)
                    .orElseThrow { ResourceNotFoundException("Subject ${item.subjectId} not found ") }

                val room = roomRepository.findById(item.roomId)
                    .orElseThrow { ResourceNotFoundException("Room ${item.roomId} not found") }

//                check if teacher schedule conflict
                if (assignmentRepository.hasTeacherConflict(
                        teacher.id!!, item.dayOfWeek, item.startTime, item.endTime
                    )
                ) {
                    throw ScheduleConflictException("Schedule conflict: teacher ${teacher.id} already")
                }

                TeacherAssignment(
                    teacher = teacher,
                    room = room,
                    subject = subject,
                    dayOfWeek = item.dayOfWeek,
                    startTime = item.startTime,
                    endTime = item.endTime
                )
            }

            assignmentRepository.saveAll(assignments).map { it.toResponseDTO() }
        }
    }

    override fun getAssignmentsByTeacher(teacherId: Long): List<TeacherAssignmentResponseDTO> {
        if (!teacherRepository.existsById(teacherId)) {
            throw ResourceNotFoundException("Teacher $teacherId not found")
        }

        return assignmentRepository.findAllByTeacherId(teacherId).map { it.toResponseDTO() }
    }

    @Transactional
    override fun updateAssignment(id: Long, dto: AssignmentUpdateDTO): TeacherAssignmentResponseDTO {
        val assignment = assignmentRepository.findById(id)
            .orElseThrow { NotFoundException("Assignment with ID $id not found") }

        val teacher = teacherRepository.findById(dto.teacherId)
            .orElseThrow { NotFoundException("Teacher $dto.teacherId not found") }

        val room = roomRepository.findById(dto.roomId)
            .orElseThrow { NotFoundException("Room $dto.roomId not found") }

        val subject = subjectRepository.findById(dto.subjectId)
            .orElseThrow { NotFoundException("Subject $dto.subjectId not found") }

        assignment.teacher = teacher
        assignment.room = room
        assignment.subject = subject
        assignment.dayOfWeek = dto.dayOfWeek
        assignment.startTime = dto.startTime
        assignment.endTime = dto.endTime

        return assignmentRepository.save(assignment).toResponseDTO()
    }

    @Transactional
    override fun deleteAssignment(id: Long) {
        val assignment = assignmentRepository.findById(id)
            .orElseThrow { NotFoundException("Assignment with ID $id not found") }

        assignmentRepository.delete(assignment)
    }

}