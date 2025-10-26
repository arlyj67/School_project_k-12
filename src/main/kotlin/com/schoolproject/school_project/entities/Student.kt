package com.schoolproject.school_project.entities

import com.schoolproject.school_project.model.GradeLevel
import com.schoolproject.school_project.model.StudentStatus
import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "students")
data class Student(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(unique = true, nullable = false)
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

//    Academic Information
    var schoolYear: String,
    var previousSchool: String,

    @Enumerated(EnumType.STRING)
    var gradeLevel: GradeLevel,
    val enrollmentDate: LocalDateTime = LocalDateTime.now(),

    @Enumerated(EnumType.STRING)
    var studentStatus: StudentStatus = StudentStatus.PENDING,

//    var isScholar: Boolean = false,

    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "user_id")
    var user: User,

    var approvedAt: LocalDateTime? = null,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    var updatedAt: LocalDateTime = LocalDateTime.now()

    )