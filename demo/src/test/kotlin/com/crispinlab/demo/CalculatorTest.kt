package com.crispinlab.demo

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class CalculatorTest :
    DescribeSpec({
        describe("kotest 를 활용한 테스트가 가능해야 한다.") {
            context("demo calculate 테스트") {
                it("두 개의 값을 더할 수 있어야 한다.") {
                    // given
                    val a = 1
                    val b = 2
                    val calculate = Calculate()

                    // when
                    val result: Int = calculate.add(a, b)

                    // then
                    result shouldBe 3
                }
            }
        }
    })
