package com.crispinlab.article.adapter.input.web.dto.response

/*
todo    :: 필드값을 private 으로 하면 직렬화가 안되는듯 하다 원인 파악 필요 getter 를 두니 되긴한다. 좀 더 나은 방법을 찾아보자
 author :: heechoel shin
 date   :: 2025-03-24T0:49:49KST
 */
class ArticleResponse<T> private constructor(
    private val resultCode: String,
    private val result: T? = null
) {
    companion object {
        fun error(errorCode: String): ArticleResponse<Unit> = ArticleResponse(errorCode)

        fun <T> error(
            errorCode: String,
            result: T
        ): ArticleResponse<T> = ArticleResponse(errorCode, result)

        fun success(): ArticleResponse<Unit> = ArticleResponse("SUCCESS")

        fun <T> success(result: T): ArticleResponse<T> = ArticleResponse("SUCCESS", result)
    }

    fun getResultCode(): String = resultCode

    fun getResult(): T? = result

    override fun toString(): String =
        """
        {"resultCode": "$resultCode", "result": ${result?.toString() ?: "null"}}
        """.trimIndent()
}
