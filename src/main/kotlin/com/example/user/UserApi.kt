package com.example.user

import com.example.exception.InvalidParamException
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.users() {
    route("/users") {
        get {
            val users = getAllUsers()
            call.respond(users)
        }

        post {
            val user = call.receive<UserDto>()
            if (user.login == null || user.password == null) {
                throw InvalidParamException("Необходимо ввести логин и пароль")
            }
            call.respond(addUser(user))
        }
    }


    route("/users/{id}") {
        /**
         * Получение пользователя по id
         */
        get {
            val id = call.parameters["id"]?.toLongOrNull() ?: throw InvalidParamException("id должно присутствовать")
            call.respond(getUserById(id))
        }
    }
}