package com.schoolproject.school_project.entities

import com.schoolproject.school_project.model.Roles
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "users")
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(
        nullable = false,
        unique = true
    )
    val username: String? = null,

    @Column(nullable = false)
    val password: String? = null,

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
        name = "user_roles",
        joinColumns = [JoinColumn(name = "user_id")]
    )
    @Column(name = "roles")
//    val roles: Set<Roles> = setOf(),
    var roles: MutableSet<Roles> = mutableSetOf(),

    var isActive: Boolean = true,

    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now(),

    )
