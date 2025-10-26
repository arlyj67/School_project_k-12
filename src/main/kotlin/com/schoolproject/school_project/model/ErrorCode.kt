package com.schoolproject.school_project.model

enum class ErrorCode(
    val code: String,
    val defaultMessage: String,
) {
    VALIDATION_ERROR("VALIDATION_ERROR", "Validation failed"),
    UNAUTHORIZED("UNAUTHORIZED", "Unauthorized access"),
    NOT_FOUND("NOT_FOUND", "Resource not found"),
    DUPLICATE("DUPLICATE", "Duplicate entry"),
    INTERNAL_ERROR("INTERNAL_ERROR", "Something went wrong"),
    FORBIDDEN("FORBIDDEN", "Access denied"),
    BAD_REQUEST("BAD_REQUEST", "Bad request format"),
    ACCESS_DENIED("ACCESS_DENIED", "Access denied"),
}