package com.schoolproject.school_project.entities

import com.schoolproject.school_project.model.Roles
import jakarta.persistence.*

@Entity
@Table(name = "permissions")
data class Permission(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, unique = true)
    val action: String,

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name = "permission_roles",
        joinColumns = [JoinColumn(name = "permission_id")]
    )
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    var allowedRoles: MutableSet<Roles> = mutableSetOf()
)