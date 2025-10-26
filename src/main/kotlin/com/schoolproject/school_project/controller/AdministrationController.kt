package com.schoolproject.school_project.controller

import com.schoolproject.school_project.dto.request.AdministrationRequestDTO
import com.schoolproject.school_project.dto.request.AdministrationUpdateDTO
import com.schoolproject.school_project.dto.response.AdministrationResponseDTO
import com.schoolproject.school_project.dto.response.AuthAdminResponseDTO
import com.schoolproject.school_project.exception_handler.responses.ApiResponse
import com.schoolproject.school_project.services.AdministrationService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/api/admin")
class AdministrationController(
    private val administrationService: AdministrationService
) {

    @PostMapping
    fun registerStaff(
        @Valid @RequestBody dto: AdministrationRequestDTO
    ): ResponseEntity<ApiResponse<AuthAdminResponseDTO>> {
        val response = administrationService.create(dto)
        return ResponseEntity.status(HttpStatus.CREATED).body(
            ApiResponse(
                status = HttpStatus.CREATED.value(),
                message = "Staff registered successfully",
                data = response
            )
        )
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('IT_ADMIN')")
    fun updateEmployee(
        @PathVariable id: Long,
        @RequestBody dto: AdministrationUpdateDTO
    ): ResponseEntity<ApiResponse<AuthAdminResponseDTO>> {
        val updated = administrationService.update(id, dto)
        return ResponseEntity.ok(
            ApiResponse(
                status = 200,
                message = "Employee updated successfully",
                data = updated
            )
        )
    }

    @GetMapping
    @PreAuthorize("hasAuthority('IT_ADMIN')")
    fun getAllEmployees(): ResponseEntity<ApiResponse<List<AuthAdminResponseDTO>>>{
        val all = administrationService.findAll()
        return ResponseEntity.ok(ApiResponse(
            status = 200,
            message = "Employees found",
            data = all
        ))

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('IT_ADMIN')")
    fun deleteEmployee(@PathVariable id: Long): ResponseEntity<ApiResponse<Unit>> {
        administrationService.delete(id)
        return ResponseEntity.ok(ApiResponse(
            status = 200,
            message = "Employee deleted successfully",
        ))
    }
}