package edu.axboot.domain.pms.guest;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import edu.axboot.controllers.dto.pms.GuestListResponseDto;
import edu.axboot.controllers.dto.pms.GuestResponseDto;
import edu.axboot.controllers.dto.pms.GuestSaveRequestDto;
import edu.axboot.controllers.dto.pms.GuestUpdateRequestDto;
import edu.axboot.domain.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GuestService extends BaseService<Guest, Long> {

    @Autowired
    private GuestRepository guestRepository;

    @Inject
    public GuestService(GuestRepository guestRepository) {
        super(guestRepository);
        this.guestRepository = guestRepository;
    }

    public GuestResponseDto findById(Long id) {
        Guest guest = guestRepository.findOne(id);
        if (guest == null) {
            throw new IllegalArgumentException("해당 고객이 없습니다. id=" + id);
        }
        return new GuestResponseDto(guest);
    }

    @Transactional(readOnly = true)
    public List<GuestListResponseDto> findBy(String srchGuestNm, String srchGuestTel, String srchEmail) {
        BooleanBuilder builder = new BooleanBuilder();

        if (isNotEmpty(srchGuestNm)) {
            builder.and(qGuest.guestNm.like("%" + srchGuestNm +"%"));
        }

        if (isNotEmpty(srchGuestTel)) {
            builder.and(qGuest.guestTel.like("%" + srchGuestTel +"%"));
        }

        if (isNotEmpty(srchEmail)) {
            builder.and(qGuest.email.like("%" + srchEmail +"%"));
        }

        List<Guest> entities = select().select(
                Projections.fields(Guest.class,
                        qGuest.id, qGuest.guestNm, qGuest.guestTel, qGuest.email, qGuest.gender, qGuest.brth, qGuest.langCd
                ))
                .from(qGuest)
                .where(builder)
                .orderBy(qGuest.guestNm.asc())
                .fetch();

        return entities.stream()
                .map(GuestListResponseDto::new)
                .collect(Collectors.toList());
    }


    @Transactional
    public Long save(GuestSaveRequestDto requestDto) {
        return guestRepository.save(requestDto.toEntity()).getId();

    }
    @Transactional
    public long update(GuestSaveRequestDto saveDto) {
        return guestRepository.save(saveDto.toEntity()).getId();
    }

    @Transactional
    public Long update(GuestUpdateRequestDto requestDto) {
        Guest guest = guestRepository.findOne(requestDto.getId());
        if (guest == null) {
            throw new IllegalArgumentException("해당 거래처가 없습니다. id=" + requestDto.getId());
        }

        guest.update(requestDto.getGuestNm(),requestDto.getGuestNmEng(),requestDto.getGuestTel(),requestDto.getEmail(),requestDto.getBrth(),requestDto.getGender(), requestDto.getLangCd(), requestDto.getRmk());

        return requestDto.getId();
    }
}