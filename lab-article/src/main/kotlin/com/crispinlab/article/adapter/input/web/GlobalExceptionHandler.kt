package com.crispinlab.article.adapter.input.web

import com.crispinlab.article.adapter.input.web.dto.response.ArticleResponse
import com.crispinlab.article.adapter.input.web.dto.response.ExceptionResponse
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@ResponseStatus(value = HttpStatus.OK)
@RestControllerAdvice
class GlobalExceptionHandler {
    private val logger: Logger = LogManager.getLogger(GlobalExceptionHandler::class.java)

    data class ValidationErrors(
        val errors: List<ValidationError>
    )

    data class ValidationError(
        val field: String,
        val value: Any?
    ) {
        companion object {
            fun of(filedError: FieldError): ValidationError =
                ValidationError(filedError.field, filedError.rejectedValue)
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidExceptionHandle(
        exception: MethodArgumentNotValidException
    ): ArticleResponse<ExceptionResponse<ValidationErrors>> {
        val errors: List<ValidationError> =
            exception.bindingResult.fieldErrors
                .stream()
                .map(ValidationError::of)
                .toList()
        val response: ArticleResponse<ExceptionResponse<ValidationErrors>> =
            ArticleResponse.error(
                errorCode = exception.fieldError?.defaultMessage ?: "ARGUMENT_NOT_VALID",
                result =
                    ExceptionResponse(
                        message = "잘못된 요청 값 입니다.",
                        data = ValidationErrors(errors)
                    )
            )
        logger.error("argument not valid: {}", response)
        return response
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun httpMessageNotReadableExceptionHandle(
        exception: HttpMessageNotReadableException
    ): ArticleResponse<ExceptionResponse<Nothing>> {
        val response: ArticleResponse<ExceptionResponse<Nothing>> =
            ArticleResponse.error(
                errorCode = "NOT_READABLE_HTTP_MESSAGE",
                result =
                    ExceptionResponse(
                        message = "잘못된 요청 값 또는 타입 입니다."
                    )
            )
        logger.error("message not readable: {}", response)
        return response
    }
}
