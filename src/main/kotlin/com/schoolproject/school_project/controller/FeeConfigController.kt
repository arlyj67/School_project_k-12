package com.schoolproject.school_project.controller

import com.schoolproject.school_project.dto.request.FeeConfigRequestDTO
import com.schoolproject.school_project.dto.response.FeeConfigResponseDTO
import com.schoolproject.school_project.exception_handler.responses.ApiResponse
import com.schoolproject.school_project.repositories.FeeConfigRepository
import com.schoolproject.school_project.services.FeeConfigService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/fee-config")
class FeeConfigController(
    private val feeConfigRepository: FeeConfigRepository,
    private val feeConfigService: FeeConfigService
) {

    @PostMapping
    fun createOrUpdate(
        @RequestBody dto: FeeConfigRequestDTO
    ): ResponseEntity<ApiResponse<FeeConfigResponseDTO>> {

        val result = feeConfigService.createOrUpdate(dto)
        return ResponseEntity.ok(
            ApiResponse.success(
                result,
                "Fee Config updated successfully!",
            )
        )
    }

    @GetMapping("/{schoolYear}")
    fun findBySchoolYear(
        @PathVariable schoolYear: String,
    ): ResponseEntity<ApiResponse<FeeConfigResponseDTO>> {
        val result = feeConfigService.findActiveBySchoolYear(schoolYear)

        return ResponseEntity.ok(ApiResponse.success(result, "Fee Config fetched successfully!"))
    }

    @GetMapping
    fun listAll(): ResponseEntity<ApiResponse<List<FeeConfigResponseDTO>>> {
        val result = feeConfigService.listAll()

        return ResponseEntity.ok(ApiResponse.success(result))
    }
}