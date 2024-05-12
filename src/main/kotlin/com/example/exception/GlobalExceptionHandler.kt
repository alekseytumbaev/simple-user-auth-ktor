package com.example.exception

import com.example.user.UserNotFoundException
import io.ktor.http.*
import io.ktor.server.plugins.requestvalidation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun StatusPagesConfig.handle() {
    exception<Throwable> { call, cause ->
        call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
    }

    exception<InvalidParamException> { call, cause ->
        call.respond(HttpStatusCode.BadRequest, "$cause")
    }

    exception<UserNotFoundException> { call, cause ->
        /**
         * Пользователь не найден
         */
        call.respond(HttpStatusCode.NotFound, "$cause")
    }

    exception<RequestValidationException> { call, cause ->
        call.respond(HttpStatusCode.BadRequest, cause.reasons.joinToString())
    }
}
