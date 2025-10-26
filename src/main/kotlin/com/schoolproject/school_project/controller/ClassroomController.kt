package com.schoolproject.school_project.controller

import com.schoolproject.school_project.dto.request.AssignmentItems
import com.schoolproject.school_project.dto.request.AssignmentUpdateDTO
import com.schoolproject.school_project.dto.request.RoomRequestDTO
import com.schoolproject.school_project.dto.request.TeacherAssignmentRequestDTO
import com.schoolproject.school_project.dto.response.RoomResponseDTO
import com.schoolproject.school_project.dto.response.TeacherAssignmentResponseDTO
import com.schoolproject.school_project.exception_handler.responses.ApiResponse
import com.schoolproject.school_project.services.ClassroomService
import com.schoolproject.school_project.services.TeacherAssignmentService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping(value = ["/api/classrooms"])
class ClassroomController(
    private val classroomService: ClassroomService,
    private val assignmentService: TeacherAssignmentService
) {

    //Get specific room
    @GetMapping("/{id}")
    @PreAuthorize("@authPermission.hasAccess('ASSIGN_TEACHER')")
    fun getRoom(@PathVariable id: Long): ResponseEntity<ApiResponse<RoomResponseDTO>> {
        val room = classroomService.findById(id)

        return ResponseEntity.ok(
            ApiResponse.success(
                data = room,
                message = "Rooms are successful fetched data!",
            )
        )
    }

    @GetMapping
    @PreAuthorize("hasAuthority('PRINCIPAL')")
    fun getAll(): ResponseEntity<ApiResponse<List<RoomResponseDTO>>> {
        val rooms = classroomService.findAll()
        return ResponseEntity.ok(
            ApiResponse(
                status = 200,
                message = "Found ${rooms.size} classrooms",
                data = rooms
            )
        )
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('PRINCIPAL')")
    fun updateRoom(
        @PathVariable id: Long,
        @RequestBody dto: RoomRequestDTO
    ): ResponseEntity<ApiResponse<RoomResponseDTO>> {
        val updated = classroomService.updateClassroom(id, dto)

        return ResponseEntity.ok(ApiResponse.success(data = updated, message = "Room updated successfully"))
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('PRINCIPAL')")
    fun createRoom(@RequestBody dto: List<RoomRequestDTO>): ResponseEntity<ApiResponse<List<RoomResponseDTO>>> {
        val response = dto.map { classroomService.create(it) }
        return ResponseEntity.ok(
            ApiResponse.success(
                data = response,
                message = "Room created successfully",
                status = 200
            )
        )
    }

    @GetMapping("/d/{id}")
    @PreAuthorize("hasAuthority('PRINCIPAL')")
    fun getRoomDelete(@PathVariable id: Long): ResponseEntity<ApiResponse<Unit>> {
        classroomService.delete(id)
        return ResponseEntity.ok(
            ApiResponse.success(
                message = "Room deleted successfully"
            )
        )
    }


    //    Assign teacher to room and subjects
    @PostMapping("/assignment")
    @PreAuthorize("hasAuthority('PRINCIPAL')")
    fun assignSubjectsToTeacher(
        @RequestBody dto: List<TeacherAssignmentRequestDTO>
    ): ResponseEntity<ApiResponse<List<TeacherAssignmentResponseDTO>>> {
        val response = assignmentService.assignSubjects(dto)
        return ResponseEntity.ok(
            ApiResponse(
                status = 200,
                message = "Subject assignment successful",
                data = response
            )
        )
    }

    @GetMapping("/assignment/{teacherId}")
    @PreAuthorize("@authPermission.hasAccess('ASSIGN_TEACHER')")
    fun getAssignmentsByTeacher(
        @PathVariable teacherId: Long
    ): ResponseEntity<ApiResponse<List<TeacherAssignmentResponseDTO>>> {

        val assignments = assignmentService.getAssignmentsByTeacher(teacherId)
        return ResponseEntity.ok(
            ApiResponse(
                status = 200,
                message = "Assignments for teacher $teacherId",
                data = assignments
            )
        )
    }

    @PutMapping("/assignment_up/{id}")
    @PreAuthorize("@authPermission.hasAccess('ASSIGN_TEACHER')")
    fun updateAssignment(
        @PathVariable id: Long,
        @RequestBody dto: AssignmentUpdateDTO
    ): ResponseEntity<ApiResponse<TeacherAssignmentResponseDTO>> {
        val updated = assignmentService.updateAssignment(id, dto)
        return ResponseEntity.ok(
            ApiResponse.success(
                data = updated,
                message = "Assignments updated successfully"
            )
        )
    }

    @DeleteMapping("/assignment_d/{id}")
    @PreAuthorize("@authPermission.hasAccess('ASSIGN_TEACHER')")
    fun deleteAssignment(@PathVariable id: Long): ResponseEntity<ApiResponse<Unit>> {
        assignmentService.deleteAssignment(id)

        return ResponseEntity.ok(
            ApiResponse.success(
                message = "Assignments deleted successfully",
            )
        )
    }

}