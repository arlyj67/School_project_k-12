package com.schoolproject.school_project.controller

import com.schoolproject.school_project.dto.request.StudentRequestDTO
import com.schoolproject.school_project.dto.request.StudentUpdateDTO
import com.schoolproject.school_project.dto.response.StudentResponseDTO
import com.schoolproject.school_project.exception_handler.responses.ApiResponse
import com.schoolproject.school_project.services.StudentService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/students")
class StudentController(
    val studentService: StudentService,
) {

    @PostMapping("/register")
    fun registerStudent(@Valid @RequestBody studentRequestDTO: StudentRequestDTO): ResponseEntity<ApiResponse<StudentResponseDTO>> {
        val response = studentService.register(studentRequestDTO)

        return ResponseEntity.status(HttpStatus.CREATED).body(
            ApiResponse.success(
                response,
                "Student Registration Successful"
            )
        )
    }

    @PutMapping("/{id}")
    fun updateStudent(
        @PathVariable id: Long,
        @Valid @RequestBody dto: StudentUpdateDTO
    ): ResponseEntity<ApiResponse<StudentResponseDTO>> {
        val response = studentService.update(id, dto)

        return ResponseEntity.ok(ApiResponse.success(
            response,
            "Student Update Successful"

        ))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<ApiResponse<String>> {
        studentService.delete(id)

        return ResponseEntity.ok(ApiResponse.success(
            "Deleted",
            "Student Deletion Successful"
        ))
    }

    @GetMapping
    fun getAllStudents(): ResponseEntity<ApiResponse<List<StudentResponseDTO>>>{
        val students = studentService.findAll()

        return ResponseEntity.ok(ApiResponse.success(
            students,
            "Student Information Successful"
        ))
    }

    @GetMapping("/{id}")
    fun getStudentById(@PathVariable id: Long): ResponseEntity<ApiResponse<StudentResponseDTO>> {
        val student = studentService.getById(id)

        return ResponseEntity.ok(ApiResponse.success(
            student,
            "Student Information Successful"
        ))


    }
}