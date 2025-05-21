package com.crispinlab.board.adapter.input.web.dto.response

import kotlinx.serialization.Serializable

@Serializable
internal class BoardResponse<T> private constructor(
    private val resultCode: String,
    private val result: T? = null
) {
    companion object {
        fun error(errorCode: String): BoardResponse<Unit> = BoardResponse(errorCode)

        fun <T> error(
            errorCode: String,
            result: T
        ): BoardResponse<T> = BoardResponse(errorCode, result)

        fun success(): BoardResponse<Unit> = BoardResponse("SUCCESS")

        fun <T> success(result: T): BoardResponse<T> = BoardResponse("SUCCESS", result)
    }

    override fun toString(): String =
        """
        {"resultCode": "$resultCode", "result": ${result?.toString() ?: "null"}}
        """.trimIndent()
}
