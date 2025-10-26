package com.schoolproject.school_project.exception_handler.responses

import com.schoolproject.school_project.model.ErrorCode

data class ApiResponse<T> (
    val status: Int,
    val message: String,
    val error: ErrorCode? = null,
    val path: String? = null,
    val data: T? = null,
    val timestamp: String = java.time.LocalDateTime.now().toString(),
){
    companion object{
        fun <T> success(
            data: T? = null,
            message: String = "Request successful",
            status: Int = 200): ApiResponse<T> {
            return ApiResponse(
                status = status,
                message = message,
                data = data,
            )
        }
    }
}