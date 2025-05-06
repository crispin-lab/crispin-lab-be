package com.crispinlab.common.validation

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Constraint(validatedBy = [IdentifierValidator::class])
annotation class Identifier(
    val message: String = "INVALID_IDENTIFIER",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
