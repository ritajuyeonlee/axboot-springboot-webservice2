package edu.axboot.domain.pms.room;

import com.querydsl.core.BooleanBuilder;
import edu.axboot.controllers.dto.pms.RoomListResponseDto;
import edu.axboot.domain.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

//@RequiredArgsConstructor
@Service
public class RoomService extends BaseService<Room, Long> {

    @Autowired
    private RoomRepository roomRepository;

    @Inject
    public RoomService(RoomRepository roomRepository) {
        super(roomRepository);
        this.roomRepository = roomRepository;
    }

    /*@Transactional
    public Long save(RoomSaveRequestDto requestDto) {
        return roomRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, RoomUpdateRequestDto requestDto) {
        Room room = roomRepository.findOne(id);

        if (room == null) {
            throw new IllegalArgumentException("해당 객실이 없습니다. id=" + id);
        }

        room.update(requestDto.getRoomTypCd(),
                requestDto.getDndYn(),
                requestDto.getEbYn(),
                requestDto.getRoomSttusCd(),
                requestDto.getClnSttusCd(),
                requestDto.getSvcSttusCd()
        );
        return id;
    }*/

    @Transactional(readOnly = true)
    public List<RoomListResponseDto> findByRoomTypCd(String roomTypCd) {

        BooleanBuilder builder = new BooleanBuilder();

        if (isNotEmpty(roomTypCd)) {
            builder.and(qRoom.roomTypCd.eq(roomTypCd));
        }

        List<Room> entitis = select()
                .from(qRoom)
                .where(builder)
                .orderBy(qRoom.roomNum.asc())
                .fetch();

        return entitis.stream()
                .map(RoomListResponseDto::new)
                .collect(Collectors.toList());

    }





}