package com.pluralsight.conferencedemo.repositories

import com.pluralsight.conferencedemo.models.Session
import com.pluralsight.conferencedemo.models.Speaker
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.lang.Nullable

interface SpeakerRepository: JpaRepository<Speaker, Long> {

    @Nullable
    @Query("SELECT s FROM Speaker s WHERE LOWER(s.first_name) LIKE LOWER(concat('%', concat(:firstname, '%')))")
    fun findByFirstName(@Param("firstname") firstname: String): List<Speaker>

    @Nullable
    @Query("SELECT s FROM Speaker s WHERE LOWER(s.last_name) LIKE LOWER(concat('%', concat(:lastname, '%')))")
    fun findByLastName(@Param("lastname") lastname: String): List<Speaker>

    @Nullable
    @Query("SELECT s FROM Speaker s WHERE LOWER(s.title) LIKE LOWER(concat('%', concat(:title, '%')))")
    fun findByTitle(@Param("title") title: String): List<Speaker>

    @Nullable
    @Query("SELECT s FROM Speaker s WHERE LOWER(s.company) LIKE LOWER(concat('%', concat(:company, '%')))")
    fun findByCompany(@Param("company") company: String): List<Speaker>
}