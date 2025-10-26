package com.schoolproject.school_project.dto.request

import com.schoolproject.school_project.model.GradeLevel
import java.time.LocalDate

data class StudentUpdateDTO(
    val firstName: String,
    val lastName: String,
    val middleName: String? = null,
    val birthday: LocalDate,
    val gender: String,
    val nationality: String,
    val phoneNumber: String,

    var parents: String,
    var parentsContactNumber: String,
    val guardianName: String,
    val guardianContactNumber: String,
    val guardianRelationship: String,

    val previousSchool: String,
    val gradeLevel: GradeLevel,
    val schoolYear: String,
    val parentName: String
)