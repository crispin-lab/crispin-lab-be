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
import org.springframework.web.servlet.NoHandlerFoundException

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

    @ExceptionHandler(Exception::class)
    fun unknownExceptionHandle(exception: Exception): ArticleResponse<ExceptionResponse<Nothing>> {
        val response: ArticleResponse<ExceptionResponse<Nothing>> =
            ArticleResponse.error(
                errorCode = "UNKNOWN_EXCEPTION",
                result =
                    ExceptionResponse(
                        message = "알 수 없는 예외 발생"
                    )
            )
        logger.error("unknown exception: {}", exception.printStackTrace())
        return response
    }

    @ExceptionHandler(NoHandlerFoundException::class)
    fun notFoundExceptionHandle(
        exception: NoHandlerFoundException
    ): ArticleResponse<ExceptionResponse<String>> {
        val response: ArticleResponse<ExceptionResponse<String>> =
            ArticleResponse.error(
                errorCode = "NOT_FOUND_EXCEPTION",
                result =
                    ExceptionResponse(
                        message = "잘못된 요청 URL 입니다.",
                        data = "request url: ${exception.requestURL}"
                    )
            )
        logger.error("not found exception: {}", exception.requestURL)
        return response
    }
}
