package com.schoolproject.school_project.mapper

import com.schoolproject.school_project.dto.request.StudentRequestDTO
import com.schoolproject.school_project.dto.request.StudentUpdateDTO
import com.schoolproject.school_project.dto.response.StudentResponseDTO
import com.schoolproject.school_project.entities.Student
import com.schoolproject.school_project.entities.User
import com.schoolproject.school_project.model.GradeLevel
import com.schoolproject.school_project.model.StudentStatus
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import java.time.LocalDate
import java.time.LocalDateTime

fun StudentRequestDTO.toEntity(
    studentNumber: String,
    user: User
): Student {
    return Student(
        studentNumber = studentNumber,
        firstName = firstName,
        lastName = lastName,
        middleName = middleName,
        suffix = suffix,
        gender = gender,
        birthday = birthday,
        birthPlace = birthPlace,
        nationality = nationality,
        religion = religion,
        contactNumber = contactNumber,
        emailAddress = emailAddress,
        address = address,
        parent = parent,
        parentContactNumber = parentContactNumber,
        guardian = guardian,
        guardianContactNumber = guardianContactNumber,
        gradeLevel = gradeLevel,
        studentStatus = StudentStatus.PENDING,
        previousSchool = previousSchool,
        schoolYear = schoolYear,
        user = user,
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now()
    )
}

fun Student.updateFrom(dto: StudentUpdateDTO) {
    firstName = firstName
    lastName = lastName
    middleName = middleName
    suffix = suffix
    gender = gender
    birthday = birthday
    birthPlace = birthPlace
    nationality = nationality
    religion = religion
    contactNumber = contactNumber
    emailAddress = emailAddress
    address = address
    parent = parent
    parentContactNumber = parentContactNumber
    guardian = guardian
    guardianContactNumber = guardianContactNumber
    gradeLevel = gradeLevel
    updatedAt = LocalDateTime.now()
}

fun Student.toResponseDTO(rawPassword: String? = null): StudentResponseDTO {
    return StudentResponseDTO(
        studentNumber = studentNumber,
        fullName = "$firstName $lastName",
        email = user.username ?: "",
        gradeLevel = gradeLevel,
        schoolYear = schoolYear,
        status = studentStatus.name,
        rawPassword = rawPassword
    )
}