package com.crispinlab.article.adapter.input.web.validation

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import java.util.regex.Pattern

class IdentifierValidator : ConstraintValidator<Identifier, Long> {
    override fun isValid(
        value: Long?,
        context: ConstraintValidatorContext?
    ): Boolean {
        val pattern: Pattern = Pattern.compile("^\\d{16,19}$")
        return value?.let {
            pattern.matcher(it.toString()).matches()
        } ?: false
    }
}
