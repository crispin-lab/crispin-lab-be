package com.crispinlab.common.validation

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Constraint(validatedBy = [NotEmptyOrBlankValidator::class])
annotation class NotEmptyOrBlank(
    val message: String = "MUST_NOT_BE_EMPTY_OR_BLANK",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
