package edu.axboot.controllers;

import com.chequer.axboot.core.api.response.ApiResponse;
import com.chequer.axboot.core.api.response.Responses;
import com.chequer.axboot.core.controllers.BaseController;
import edu.axboot.controllers.dto.pms.RoomListResponseDto;
import edu.axboot.controllers.dto.pms.RoomSaveRequestDto;
import edu.axboot.controllers.dto.pms.RoomUpdateRequestDto;
import edu.axboot.domain.pms.room.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class RoomController extends BaseController {

    @Inject
    private RoomService roomService;

    @PostMapping("/api/v1/room")
    public ApiResponse save(@RequestBody RoomSaveRequestDto requestDto) {
        roomService.save(requestDto);
        return ok();
    }

    @PutMapping("/api/v1/room/{id}")
    public ApiResponse update(@PathVariable Long id, @RequestBody RoomUpdateRequestDto requestDto) {
        roomService.update(id, requestDto);
        return ok();
    }
    @GetMapping("/api/v1/room")
    public Responses.ListResponse list(@RequestParam(value = "companyNm", required = false) String roomTypCd) {
        List<RoomListResponseDto> list = roomService.findByRoomTypCd(roomTypCd);
        return Responses.ListResponse.of(list);
    }
}