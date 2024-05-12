package com.example.user

import com.example.Users
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

fun getAllUsers(): List<UserDto> {
    return transaction {
        Users.selectAll()
            .map { UserDto(it[Users.id], it[Users.login], it[Users.password]) }
            .toList()
    }
}

fun getUserById(id: Long): UserDto {
    val fieldSet = Users.select { Users.id eq id }.firstOrNull()
    if (fieldSet == null)
        throw UserNotFoundException("Пользователь не найден")
    return UserDto(fieldSet[Users.id], fieldSet[Users.login], fieldSet[Users.password])
}

fun addUser(user: UserDto): UserDto = transaction {
    val userId = Users.insert {
        it[login] = user.login ?: ""
        it[password] = user.password ?: ""
    }[Users.id]
    getUserById(userId)
}