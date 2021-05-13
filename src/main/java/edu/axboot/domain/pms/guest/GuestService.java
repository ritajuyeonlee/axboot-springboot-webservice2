package edu.axboot.domain.pms.guest;

import edu.axboot.controllers.dto.EducationResponseDto;
import edu.axboot.domain.education.book.EducationBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.axboot.domain.BaseService;
import javax.inject.Inject;
import com.chequer.axboot.core.parameter.RequestParams;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GuestService extends BaseService<Guest, Long> {

    @Autowired
    private GuestRepository guestRepository;

    @Inject
    public GuestService(GuestRepository guestRepository) {
        super(guestRepository);
        this.guestRepository = guestRepository;
    }

    public List<Guest> gets(RequestParams<Guest> requestParams) {
        return findAll();
    }

    public Guest findById(Long id) {
        Guest guest = guestRepository.findOne(id);
        if (guest == null) {
            throw new IllegalArgumentException("해당 고객이 없습니다. id=" + id);
        }
        return guest;
    }
}