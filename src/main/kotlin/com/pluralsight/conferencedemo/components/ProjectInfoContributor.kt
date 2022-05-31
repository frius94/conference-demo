package com.pluralsight.conferencedemo.components

import org.springframework.boot.actuate.info.Info
import org.springframework.boot.actuate.info.InfoContributor

class ProjectInfoContributor : InfoContributor {
    override fun contribute(builder: Info.Builder?) {
        //add new info
        builder!!.withDetail("project_name", "...")
            .withDetail("owned_by_team", "...")
            .build()
    }
}