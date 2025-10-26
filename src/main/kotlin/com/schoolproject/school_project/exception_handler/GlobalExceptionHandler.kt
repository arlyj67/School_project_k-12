package com.schoolproject.school_project.exception_handler

import com.schoolproject.school_project.exception_handler.responses.ApiResponse
import com.schoolproject.school_project.model.ErrorCode
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(
        ex: IllegalArgumentException,
        request: WebRequest
    ): ResponseEntity<ApiResponse<Nothing>> {
        return ResponseEntity(
            ApiResponse(
                status = HttpStatus.BAD_REQUEST.value(),
                message = ex.message ?: "Bad request"
            ),
            HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationErrors(
        ex: MethodArgumentNotValidException,
        request: WebRequest
    ): ResponseEntity<ApiResponse<Nothing>> {
        val message = ex.bindingResult.fieldErrors.joinToString("; ") {
            "${it.field}: ${it.defaultMessage}"
        }

        val response = ApiResponse<Nothing>(
            status = HttpStatus.BAD_REQUEST.value(),
            message = message,
            error = ErrorCode.VALIDATION_ERROR,
            path = request.getDescription(false).removePrefix("uri="),
            data = null
        )

        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(Exception::class)
    fun handleGenericError(
        ex: Exception,
        request: WebRequest
    ): ResponseEntity<ApiResponse<Nothing>> {

        ex.printStackTrace()

        return ResponseEntity(
            ApiResponse(
                status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
                message = "An unexpected error occurred.",
                error = ErrorCode.INTERNAL_ERROR,
                data = null,
                path = request.getDescription(false).removePrefix("uri="),
            ),
            HttpStatus.INTERNAL_SERVER_ERROR
        )
    }

    @ExceptionHandler(RoomAlreadyExistsException::class)
    fun handleRoomAlreadyExistsException(
        ex: RoomAlreadyExistsException,
        request: WebRequest
    ): ResponseEntity<ApiResponse<Nothing>> {
        val error = ApiResponse<Nothing>(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            message = ex.message ?: "Room already exists",
        )

        return ResponseEntity(error, HttpStatus.CONFLICT)
    }

    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleNotFoundException(
        ex: ResourceNotFoundException,
        request: WebRequest
    ): ResponseEntity<ApiResponse<Nothing>> {
        val response = ApiResponse<Nothing>(
            status = HttpStatus.NOT_FOUND.value(),
            message = ex.message ?: "Resource not found",
            error = ErrorCode.NOT_FOUND,
            path = request.getDescription(false).removePrefix("uri="),
        )
        return ResponseEntity(response, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(AccessDeniedException::class)
    fun handleAcccessDeniedException(
        ex: AccessDeniedException,
        request: WebRequest
    ): ResponseEntity<ApiResponse<Nothing>> {
        return ResponseEntity(
            ApiResponse(
                status = HttpStatus.FORBIDDEN.value(),
                message = "You do not have permission to perform this action",
                error = ErrorCode.ACCESS_DENIED,
                path = request.getDescription(false).removePrefix("uri="),
            ),
            HttpStatus.FORBIDDEN
        )
    }
}

