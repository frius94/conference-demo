package com.pluralsight.conferencedemo.models

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.hibernate.annotations.Type
import javax.persistence.*

@Entity
@Table(name = "speakers")
@JsonIgnoreProperties(value = ["hibernateLazyInitializer", "handler"])
class Speaker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    var speaker_id: Long? = null

    @Column
    var first_name: String? = null

    @Column
    var last_name: String? = null

    @Column
    var title: String? = null

    @Column
    var company: String? = null

    @Column
    var speaker_bio: String? = null

    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    var speaker_photo: Array<Byte>? = null

    @ManyToMany(mappedBy = "speakers")
    @JsonIgnore
    var sessions: List<Session>? = null
}