package com.pluralsight.conferencedemo.repositories

import com.pluralsight.conferencedemo.models.Session
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.lang.Nullable

interface SessionRepository: JpaRepository<Session, Long> {

    @Nullable
    @Query("SELECT s FROM Session s WHERE LOWER(s.session_name) LIKE LOWER(concat('%', concat(:name, '%')))")
    fun findByName(@Param("name") name: String): List<Session>
}