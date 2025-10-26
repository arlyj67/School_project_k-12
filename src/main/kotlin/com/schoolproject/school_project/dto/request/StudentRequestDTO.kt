package com.schoolproject.school_project.dto.request

import com.schoolproject.school_project.model.GradeLevel
import com.schoolproject.school_project.model.StudentStatus
import java.time.LocalDate
import java.time.LocalDateTime

data class StudentRequestDTO (
    var studentNumber: String,
    var firstName: String,
    var lastName: String,
    var middleName: String? = null,
    var suffix: String? = null,
    var gender: String,
    var birthday: LocalDate,
    var birthPlace: String? = null,
    var nationality: String,
    var religion: String,

//    Contact Info
    var contactNumber: String,
    var emailAddress: String,
    var address: String,

//    Family Information
    var parent: String,
    var parentContactNumber: String,
    var guardian: String,
    var guardianContactNumber: String,

    var gradeLevel: GradeLevel,
    val enrollmentDate: LocalDateTime = LocalDateTime.now(),
    val schoolYear: String,
    var previousSchool: String,

    var studentStatus: StudentStatus,
    var approvedAt: LocalDateTime? = null,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    var updatedAt: LocalDateTime = LocalDateTime.now()
    )
