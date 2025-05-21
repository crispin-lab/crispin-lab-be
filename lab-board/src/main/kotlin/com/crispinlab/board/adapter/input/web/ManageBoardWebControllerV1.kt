package com.crispinlab.board.adapter.input.web

import com.crispinlab.board.adapter.input.web.dto.request.CreateBoardRequest
import com.crispinlab.board.adapter.input.web.dto.request.DeleteBoardRequest
import com.crispinlab.board.adapter.input.web.dto.request.UpdateBoardRequest
import com.crispinlab.board.adapter.input.web.dto.response.BoardResponse
import com.crispinlab.board.adapter.input.web.dto.response.DeleteBoardResponse
import com.crispinlab.board.adapter.input.web.dto.response.ManageBoardResponse
import com.crispinlab.board.application.port.input.ManageBoardUseCase
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PatchMapping
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
        @RequestBody @Valid request: CreateBoardRequest
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

    @PatchMapping(
        path = ["/board"],
        produces = ["application/json", "application/vnd.crispin-lab.com-v1+json"]
    )
    fun updateBoard(
        @RequestBody @Valid request: UpdateBoardRequest
    ): BoardResponse<ManageBoardResponse> {
        val response: ManageBoardUseCase.UpdateResponse =
            manageBoardUseCase.update(
                ManageBoardUseCase.UpdateRequest(
                    id = request.id,
                    name = request.name,
                    description = request.description,
                    visibility = request.visibility?.name,
                    modifiedAt = request.modifiedAt
                )
            )
        return BoardResponse.success(
            result =
                ManageBoardResponse(
                    id = response.id.toString(),
                    datetime = response.modifiedAt
                )
        )
    }

    @DeleteMapping(
        path = ["/board"],
        produces = ["application/json", "application/vnd.crispin-lab.com-v1+json"]
    )
    fun deleteBoard(
        @RequestBody @Valid request: DeleteBoardRequest
    ): BoardResponse<DeleteBoardResponse> {
        val response: ManageBoardUseCase.DeleteResponse =
            manageBoardUseCase.delete(
                ManageBoardUseCase.DeleteRequest(
                    id = request.id
                )
            )
        return BoardResponse.success(
            result =
                DeleteBoardResponse(
                    id = response.id.toString(),
                    status = response.status,
                    message = response.message
                )
        )
    }
}
