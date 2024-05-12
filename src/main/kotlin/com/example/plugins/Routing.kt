package com.example.plugins

import com.example.exception.handle
import com.example.user.UserDto
import com.example.user.users
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.resources.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    install(Resources)
    install(RequestValidation) {
        validate<UserDto> {
            if (it.login == null || it.password == null)
                ValidationResult.Invalid("Необходимо ввести логин и пароль")
            else
                ValidationResult.Valid
        }
    }
    install(StatusPages) {
        handle()
    }

    routing {
        users()
    }
}

