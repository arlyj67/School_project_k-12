package com.schoolproject.school_project.controller

import com.schoolproject.school_project.dto.request.JuniorPaymentRequestDTO
import com.schoolproject.school_project.dto.request.SeniorPaymentRequestDTO
import com.schoolproject.school_project.dto.response.JuniorPaymentResponseDTO
import com.schoolproject.school_project.dto.response.SeniorPaymentResponseDTO
import com.schoolproject.school_project.exception_handler.responses.ApiResponse
import com.schoolproject.school_project.model.JuniorPaymentTerm
import com.schoolproject.school_project.model.SeniorPaymentTerm
import com.schoolproject.school_project.services.JuniorPaymentService
import com.schoolproject.school_project.services.SeniorPaymentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/payments")
class JuniorPaymentController(
    private val juniorPaymentService: JuniorPaymentService,
    private val seniorPaymentService: SeniorPaymentService
) {

    @PostMapping("/junior-student")
    fun juniorPayment(
        @RequestBody dto: JuniorPaymentRequestDTO
    ): ResponseEntity<ApiResponse<List<JuniorPaymentResponseDTO>>> {
        val response = juniorPaymentService.recordPayment(dto)
        return ResponseEntity.ok(
            ApiResponse.success(
                data = response,
                message = "Junior payment recorded successfully"
            )
        )
    }

    @GetMapping("/junior-student/{studentId}/term/{term}")
    fun findByStudentAndTerm(
        @PathVariable studentId: Long,
        @PathVariable term: JuniorPaymentTerm
    ): ResponseEntity<ApiResponse<List<JuniorPaymentResponseDTO>>> {
        val response = juniorPaymentService.findByStudentAndTerm(studentId, term)
        return ResponseEntity.ok(ApiResponse.success(
            data = response,
            message = "Payments for student retrieved"
        ))
    }

    @GetMapping("/junior-student/{studentNumber}")
    fun findAllByStudentNumber(
        @PathVariable studentNumber: String
    ): ResponseEntity<ApiResponse<List<JuniorPaymentResponseDTO>>> {
        val response = juniorPaymentService.findByStudentNumber(studentNumber)
        return ResponseEntity.ok(ApiResponse.success(
            data = response,
            message = "All payments for student retrieved"
        ))
    }


//    <-- Senior section for payment -->

    @PostMapping("/senior-student")
    fun seniorPayment(
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

    @GetMapping("/senior-student/{studentId}/term/{term}")
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

    @GetMapping("/senior-student/{studentNumber}")
    fun findAllByStudentNumberSenior(
        @PathVariable studentNumber: String
    ): ResponseEntity<ApiResponse<List<SeniorPaymentResponseDTO>>> {
        val response = seniorPaymentService.findByStudentNumber(studentNumber)
        return ResponseEntity.ok(ApiResponse.success(
            data = response,
            message = "All payments for student retrieved"))
    }

}