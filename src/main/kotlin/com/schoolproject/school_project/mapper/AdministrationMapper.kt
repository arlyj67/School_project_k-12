package com.schoolproject.school_project.mapper

import com.schoolproject.school_project.dto.response.AdministrationResponseDTO
import com.schoolproject.school_project.entities.Administration

fun Administration.toResponseDTO(email: String): AdministrationResponseDTO {
    return AdministrationResponseDTO(
        adminID = this.adminID,
        firstName = this.firstName,
        lastName = this.lastName,
        middleName = this.middleName,
        birthday = this.birthday,
        gender = this.gender,
        contactNumber = this.contactNumber,
        roles = this.roles,
        hireDate = this.hireDate,
        email = email,
        createDate = this.createdAt,
        updateDate = this.updatedAt,
    )
}