package com.schoolproject.school_project.entities

import com.schoolproject.school_project.model.Roles
import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "administration")
data class Administration(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(unique = true,
        nullable = false)
    var adminID: String,

    var firstName: String,
    var lastName: String,
    var middleName: String? = null,
    var birthday: String,
    var gender: String,
    var contactNumber: String,

    @ElementCollection( fetch = FetchType.EAGER)
    @CollectionTable(name = "administration_roles", joinColumns = [JoinColumn(name = "admin_id")])
    @Enumerated(EnumType.STRING)
    var roles: MutableSet<Roles> = mutableSetOf(),

    var hireDate: LocalDate,

    @OneToOne(
        cascade = [CascadeType.ALL],
        fetch = FetchType.LAZY
    )
    @JoinColumn(name = "user_id")
    var user: User,


    val createdAt: LocalDateTime = LocalDateTime.now(),
    var updatedAt: LocalDateTime = LocalDateTime.now(),
)