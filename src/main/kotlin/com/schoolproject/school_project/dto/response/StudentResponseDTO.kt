package com.schoolproject.school_project.dto.response

import com.schoolproject.school_project.model.GradeLevel
import com.schoolproject.school_project.model.StudentStatus
import java.time.LocalDate
import java.time.LocalDateTime

data class StudentResponseDTO(
    val studentNumber: String?,
    val fullName: String,
    val email: String,
    val gradeLevel: GradeLevel,
    val schoolYear: String,
    val status: String,
    val rawPassword: String? = null
)
//
//class StudentResponseDTO(
//    var studentNumber: String,
//
//    var firstName: String,
//    var lastName: String,
//    var middleName: String? = null,
//    var suffix: String? = null,
//    var gender: String,
//    var birthday: LocalDate,
//    var birthPlace: String? = null,
//    var nationality: String,
//    var religion: String? = null,
//
////    Contact Info
//    var contactNumber: String,
//    var emailAddress: String,
//    var address: String,
//
////    Family Information
//    var parent: String,
//    var parentContact: String,
//    var guardian: String,
//    var guardianContactNumber: String,
//
//    var gradeLevel: GradeLevel,
//    val enrollmentDate: LocalDateTime = LocalDateTime.now(),
//
//    var previousSchool: String? = null,
//
////    Student Status
//    var studentStatus: StudentStatus,// StudentStatus enum = pending,  rejected, accepted
//    var approvedAt: LocalDateTime? = null,
//    val createdAt: LocalDateTime = LocalDateTime.now(),
//    var updatedAt: LocalDateTime = LocalDateTime.now()
//)
