package com.schoolproject.school_project.services.implementation

import com.schoolproject.school_project.dto.request.StudentRequestDTO
import com.schoolproject.school_project.dto.request.StudentUpdateDTO
import com.schoolproject.school_project.dto.response.StudentResponseDTO
import com.schoolproject.school_project.entities.Student
import com.schoolproject.school_project.entities.User
import com.schoolproject.school_project.exception_handler.NotFoundException
import com.schoolproject.school_project.model.Roles
import com.schoolproject.school_project.repositories.StudentRepository
import com.schoolproject.school_project.repositories.UserRepository
import com.schoolproject.school_project.services.StudentService
import jakarta.transaction.Transactional
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.io.File
import java.time.LocalDateTime
import java.util.*

@Service
class StudentServiceImpl(
    private val studentRepository: StudentRepository,
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) : StudentService {

    @Transactional
    override fun register(dto: StudentRequestDTO): StudentResponseDTO {

        val rawPassword = UUID.randomUUID().toString().substring(0, 16)
        val encodedPassword = passwordEncoder.encode(rawPassword)

        val user = userRepository.save(
            User(
                username = generateEmail(dto.firstName, dto.lastName),
                password = encodedPassword,
                roles = mutableSetOf(Roles.STUDENT),
                isActive = false
            )
        )

        var student = Student(
            studentNumber = dto.studentNumber,

            firstName = dto.firstName,
            lastName = dto.lastName,
            middleName = dto.middleName,
            suffix = dto.suffix,
            gender = dto.gender,
            birthday = dto.birthday,
            birthPlace = dto.birthPlace,
            nationality = dto.nationality,
            religion = dto.religion,

            contactNumber = dto.contactNumber,
            emailAddress = dto.emailAddress,
            address = dto.address,

            parent = dto.parent,
            parentContactNumber = dto.parentContactNumber,
            guardian = dto.guardian,
            guardianContactNumber = dto.guardianContactNumber,
            gradeLevel = dto.gradeLevel,
            studentStatus = dto.studentStatus,
            user = user,
            schoolYear = dto.schoolYear,
            previousSchool = dto.previousSchool,
        )

        student = studentRepository.save(student)

        student.studentNumber = "STD-" + student.id!!.toString().padStart(7 , '0')

        student = studentRepository.save(student)

        studentCredentialToFile(student.studentNumber, user.username!!, rawPassword)

        return StudentResponseDTO(
            studentNumber = student.studentNumber,
            fullName = "${student.firstName} ${student.lastName}",
            email = user.username.toString(),
            gradeLevel = student.gradeLevel,
            schoolYear = student.schoolYear,
            status = student.studentStatus.name,
            rawPassword = rawPassword
        )
    }

    @Transactional
    override fun update(id: Long, dto: StudentUpdateDTO): StudentResponseDTO {
        val student = studentRepository.findById(id)
            .orElseThrow { NotFoundException("Student with id $id not found") }

        student.apply {
            firstName = dto.firstName
            lastName = dto.lastName
            middleName = dto.middleName
            birthday = dto.birthday
            gender = dto.gender
            nationality = dto.nationality
            contactNumber = dto.phoneNumber
            guardian = dto.guardianName
            guardianContactNumber = dto.guardianContactNumber
            previousSchool = dto.previousSchool
            gradeLevel = dto.gradeLevel
            schoolYear = dto.schoolYear
            parent = dto.parents
            parentContactNumber = dto.parentsContactNumber
            updatedAt = LocalDateTime.now()
        }

        val saved = studentRepository.save(student)

        return StudentResponseDTO(
            studentNumber = saved.studentNumber,
            fullName = "${saved.firstName} ${saved.lastName}",
            email = saved.user.username.toString(),
            gradeLevel = saved.gradeLevel,
            schoolYear = saved.schoolYear,
            status = saved.studentStatus.name,
            rawPassword = null.toString() // Don’t return password on update
        )
    }

    override fun delete(id: Long) {
        val student = studentRepository.findById(id)
            .orElseThrow { NotFoundException("Student with id $id not found") }

        studentRepository.delete(student)
    }

    override fun findAll(): List<StudentResponseDTO> {
        return studentRepository.findAll()
            .map {
                StudentResponseDTO(
                    studentNumber = it.studentNumber,
                    fullName = "${it.firstName} ${it.lastName}",
                    email = it.user.username.toString(),
                    gradeLevel = it.gradeLevel,
                    schoolYear = it.schoolYear,
                    status = it.studentStatus.name,
                    rawPassword = null.toString()
                )
            }
    }

    override fun getAll(): List<StudentResponseDTO> {
        TODO("Not yet implemented")
    }

    override fun getById(id: Long): StudentResponseDTO? {
        TODO("Not yet implemented")
    }

    override fun findByStatus(status: String): List<StudentResponseDTO> {
        TODO("Not yet implemented")
    }

    override fun findByRefNumber(refNumber: String): StudentResponseDTO {
        TODO("Not yet implemented")
    }

//    private fun generateRefNumber(): String {
//        val prefix = "REF"
//        val timestamp = System.currentTimeMillis()
//
//        return "$prefix-$timestamp"
//    }

    private fun generateEmail(firstName: String, lastName: String): String {
        val cleanFirst = firstName.lowercase().replace("\\s".toRegex(), "")
        val cleanLast = lastName.lowercase().replace("\\s".toRegex(), "")
        return "$cleanFirst.$cleanLast@student.learnixplus.edu.ph"
    }

//    private fun generateStudentID(): String {
//        val lastId = studentRepository.findAll()
//            .mapNotNull { it.studentNumber?.removePrefix("STD-")?.toIntOrNull() }
//            .maxOrNull() ?: 0
//        return "STD-%04d".format(lastId + 1)
//    }

    fun studentCredentialToFile(studentNumber: String, email: String, password: String) {
        val fileName = "student-$studentNumber.txt"
        val fileContent = """
        Welcome to Learnix+

        Your account has been created successfully.

        Email: $email
        Password: $password

        Status: PENDING

        Please change your password after login.
    """.trimIndent()

        val file = File("accounts/$fileName")
        file.parentFile.mkdirs()
        file.writeText(fileContent)
    }

}