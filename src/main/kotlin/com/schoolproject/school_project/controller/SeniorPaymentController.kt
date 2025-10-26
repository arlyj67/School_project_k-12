package com.schoolproject.school_project.controller

import com.schoolproject.school_project.dto.request.SeniorPaymentRequestDTO
import com.schoolproject.school_project.dto.response.SeniorPaymentResponseDTO
import com.schoolproject.school_project.exception_handler.responses.ApiResponse
import com.schoolproject.school_project.model.SeniorPaymentTerm
import com.schoolproject.school_project.services.SeniorPaymentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/payments/senior")
class SeniorPaymentController(
    private val seniorPaymentService: SeniorPaymentService
) {

    @PostMapping("/record")
    fun recordPayment(
        @RequestBody dto: SeniorPaymentRequestDTO
    ): ResponseEntity<ApiResponse<List<SeniorPaymentResponseDTO>>> {
        val response = seniorPaymentService.recordPayment(dto)
        return ResponseEntity.ok(
            ApiResponse.success(
                data = response,
                message = "Senior payment recorded successfully"
            )
        )
    }

    @GetMapping("students/{studentId}/term/{term}")
    fun findByStudentAndTerm(
        @PathVariable studentId: Long,
        @PathVariable term: SeniorPaymentTerm
    ): ResponseEntity<ApiResponse<List<SeniorPaymentResponseDTO>>> {
        val response = seniorPaymentService.findByStudentAndTerm(studentId, term)
        return ResponseEntity.ok(
            ApiResponse.success(
                data = response,
                message = "Payments for student retrieved"
            )
        )
    }

    @GetMapping("/student/{studentNumber}")
    fun findAllByStudentNumber(
        @PathVariable studentNumber: String
    ): ResponseEntity<ApiResponse<List<SeniorPaymentResponseDTO>>> {
        val response = seniorPaymentService.findByStudentNumber(studentNumber)
        return ResponseEntity.ok(ApiResponse.success(
            data = response,
            message = "All payments for student retrieved"))
    }
}