package com.pluralsight.conferencedemo.models

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*

@Entity
@Table(name = "sessions")
@JsonIgnoreProperties(value = ["hibernateLazyInitializer", "handler"])
class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    var session_id: Long? = null

    @Column
    var session_name: String? = null

    @Column
    var session_description: String? = null

    @Column
    var session_length: Int? = null

    @ManyToMany
    @JoinTable(
        name = "session_speakers",
        joinColumns = [JoinColumn(name = "session_id")],
        inverseJoinColumns = [JoinColumn(name = "speaker_id")]
    )
    var speakers: List<Speaker>? = null
}