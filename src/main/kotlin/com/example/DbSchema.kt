package com.example

import org.jetbrains.exposed.sql.Table

object Users : Table() {
    val id = long("id").autoIncrement()
    val login = varchar("name", length = 50)
    val password = varchar("password", length = 50)

    override val primaryKey = PrimaryKey(id)
}