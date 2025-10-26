package com.schoolproject.school_project.controller

import com.schoolproject.school_project.dto.request.SubjectCreateDTO
import com.schoolproject.school_project.dto.response.SubjectResponseDTO
import com.schoolproject.school_project.model.GradeLevel
import com.schoolproject.school_project.exception_handler.responses.ApiResponse
import com.schoolproject.school_project.services.SubjectService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/api/subjects")
class SubjectsController(
    private val subjectService: SubjectService
) {

    @PostMapping
    fun create(@RequestBody dto: List<SubjectCreateDTO>): ResponseEntity<ApiResponse<List<SubjectResponseDTO>>> {
        val response = dto.map { subjectService.create(it) }
        return ResponseEntity.ok(
            ApiResponse.success(
                data = response,
                message = "Room created successfully",
                status = 200
            )
        )
    }

    @GetMapping
    fun getAll(): ResponseEntity<ApiResponse<List<SubjectResponseDTO>>> {
        val response = subjectService.findAll()
        return ResponseEntity.ok(
            ApiResponse(
                status = HttpStatus.OK.value(),
                message = "All subjects found",
                data = response
            )
        )
    }

    @GetMapping("/grade/{level}")
    fun getByGradeLevel(@PathVariable level: GradeLevel): ResponseEntity<ApiResponse<List<SubjectResponseDTO>>>{
        val response = subjectService.findByGradeLevel(level)
        return ResponseEntity.ok(
            ApiResponse(
                status = HttpStatus.OK.value(),
                message = "Subjects for grade level ${level.name.replace("_", " ")}",
                data = response
            )
        )
    }

//    @PostMapping("/assign-teacher")
//    fun assignTeacher(@RequestBody dto: SubjectAssignDTO): ResponseEntity<ApiResponse<SubjectResponseDTO>> {
//
//
//    }
}