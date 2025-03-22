package com.crispinlab.article.common.util

object PageLimitCalculator {
    fun calculatePageLimit(
        page: Long,
        pageSize: Long,
        movablePageCount: Long = 10
    ): Long = (((page - 1) / movablePageCount) + 1) * pageSize * movablePageCount + 1
}
