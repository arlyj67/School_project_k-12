package com.schoolproject.school_project.exception_handler

class NotFoundException(message: String) : RuntimeException(message)

class ResourceNotFoundException(message: String) : RuntimeException(message)
class ScheduleConflictException(message: String) : RuntimeException(message)