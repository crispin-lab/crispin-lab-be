package com.crispinlab.board.adapter.input.web

import com.crispinlab.board.adapter.input.web.dto.request.CreateBoardRequest
import com.crispinlab.board.adapter.input.web.dto.response.BoardResponse
import com.crispinlab.board.adapter.input.web.dto.response.ManageBoardResponse
import com.crispinlab.board.application.port.input.ManageBoardUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
internal class ManageBoardWebControllerV1(
    private val manageBoardUseCase: ManageBoardUseCase
) {
    @PostMapping(
        path = ["/board"],
        produces = ["application/json", "application/vnd.crispin-lab.com-v1+json"]
    )
    fun createBoard(
        @RequestBody request: CreateBoardRequest
    ): BoardResponse<ManageBoardResponse> {
        val response: ManageBoardUseCase.CreateResponse =
            manageBoardUseCase.create(
                ManageBoardUseCase.CreateRequest(
                    name = request.name,
                    description = request.description,
                    visibilityType = request.visibilityType.name
                )
            )
        return BoardResponse.success(
            result =
                ManageBoardResponse(
                    id = response.id.toString(),
                    datetime = response.createdAt
                )
        )
    }
}
