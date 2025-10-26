package com.schoolproject.school_project.repositories

import com.schoolproject.school_project.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository: JpaRepository<User, Long> {
    fun findByUsername(username: String): Optional<User>
//    fun existsbyUsername(username: String): Boolean
//    fun roles(roles: MutableSet<Roles>): MutableList<User>
}