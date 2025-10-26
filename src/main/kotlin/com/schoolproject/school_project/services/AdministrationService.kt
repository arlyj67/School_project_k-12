package com.schoolproject.school_project.services

import com.schoolproject.school_project.dto.request.AdministrationRequestDTO
import com.schoolproject.school_project.dto.request.AdministrationUpdateDTO
import com.schoolproject.school_project.dto.response.AuthAdminResponseDTO
import com.schoolproject.school_project.entities.Administration
import com.schoolproject.school_project.entities.User
import com.schoolproject.school_project.exception_handler.NotFoundException
import com.schoolproject.school_project.repositories.AdministrationRepository
import com.schoolproject.school_project.repositories.UserRepository
import jakarta.transaction.Transactional
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.UUID

@Service
class AdministrationService(
    private val administrationRepository: AdministrationRepository,
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
) {

    @Transactional
    fun create(dto: AdministrationRequestDTO): AuthAdminResponseDTO {
        val generateAdminID = generateAdminID()

        val rawPassword = UUID.randomUUID().toString().substring(0, 16)
        val encodedPassword = passwordEncoder.encode(rawPassword)

        val userName = generateUserName(dto.firstName, dto.lastName)

//        create and save user
//        this is auto generate after register user or admin
        val user = userRepository.save(
            User(
                username = userName,
                password = encodedPassword,
                roles = dto.roles.toMutableSet(),
                isActive = true
            )
        )

//        create and save admin or stuff
        val administration = Administration(
            adminID = generateAdminID,
            firstName = dto.firstName,
            lastName = dto.lastName,
            middleName = dto.middleName,
            birthday = dto.birthday,
            gender = dto.gender,
            contactNumber = dto.contactNumber,
            roles = dto.roles.toMutableSet(),
            hireDate = dto.hireDate,
            user = user,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )

        val saved = administrationRepository.save(administration)

//        show user account details after registration
        return AuthAdminResponseDTO(
            adminID = saved.adminID,
            fullName = "${saved.firstName} ${saved.lastName}",
            email = userName,
            roles = saved.roles.map { it.name }.toSet(),
            rawPassword = rawPassword
        )
    }

    fun generateUserName(firstName: String, lastName: String): String {
        val cleanFirst = firstName.lowercase().replace("\\s".toRegex(), "")
        val cleanLast = lastName.lowercase().replace("\\s".toRegex(), "")

        return "$cleanFirst.$cleanLast@learnixplus.edu.ph"
    }

    fun generateAdminID(): String {
        val lastId = administrationRepository.findAll()
            .mapNotNull { it.adminID.removePrefix("ADM-").toIntOrNull() }
            .maxOrNull() ?: 0
        return "ADM-%04d".format(lastId + 1)
    }

    @Transactional
    fun update(id: Long, dto: AdministrationUpdateDTO): AuthAdminResponseDTO {
        val admin = administrationRepository.findById(id)
            .orElseThrow { NotFoundException("Administration with id $id not found") }

        dto.roles.let {
            admin.user.roles = dto.roles.toMutableSet()
            userRepository.save(admin.user)
        }

        admin.firstName = dto.firstName
        admin.lastName = dto.lastName
        admin.middleName = dto.middleName
        admin.birthday = dto.birthday
        admin.gender = dto.gender
        admin.contactNumber = dto.contactNumber
        admin.roles = dto.roles.toMutableSet()
        admin.updatedAt = LocalDateTime.now()

        val saved = administrationRepository.save(admin)

        return AuthAdminResponseDTO(
            adminID = saved.adminID,
            fullName = "${saved.firstName} ${saved.lastName}",
            email = saved.user.username.toString(),
            roles = saved.roles.map { it.name }.toSet(),
            rawPassword = null.toString(),
        )

    }

    fun delete(id: Long){
        val admin = administrationRepository.findById(id)
            .orElseThrow { NotFoundException("Administration with id $id not found") }

        administrationRepository.delete(admin)
    }

    fun findAll(): List<AuthAdminResponseDTO> {
        return administrationRepository.findAll()
            .map {
                AuthAdminResponseDTO(
                    adminID = it.adminID,
                    fullName = "${it.firstName} ${it.lastName}",
                    email = it.user.username.toString(),
                    roles = it.roles.map { roles -> roles.name }.toSet(),
                    rawPassword = null.toString(),
                )
            }
    }


}