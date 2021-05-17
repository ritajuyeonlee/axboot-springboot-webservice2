package edu.axboot.domain.pms.guest;

import com.chequer.axboot.core.parameter.RequestParams;
import edu.axboot.domain.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
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
        List<Guest> list2 = this.getFilter(findAll(), requestParams.getString("guestNm", ""), 1);
        List<Guest> list3 = this.getFilter(list2, requestParams.getString("guestTel", ""), 2);
        List<Guest> list4 = this.getFilter(list3, requestParams.getString("email", ""), 3);
        return list4;
    }
    private List<Guest> getFilter(List<Guest> sources, String filter, int typ) {
        List<Guest> targets = new ArrayList<Guest>();
        for (Guest entity : sources) {
            if ("".equals(filter)) {
                targets.add(entity);
            } else {
                if (typ == 1) {

                    if (StringUtils.isNotEmpty(entity.getGuestNm()) && entity.getGuestNm().contains(filter)) {
                        targets.add(entity);
                    }
                } else if (typ == 2) {
                    if (StringUtils.isNotEmpty(entity.getGuestTel()) && entity.getGuestTel().contains(filter)) {
                        targets.add(entity);
                    }
                } else if (typ == 3) {
                    if (StringUtils.isNotEmpty(entity.getEmail()) && entity.getEmail().contains(filter)) {
                        targets.add(entity);
                    }
                }
            }
        }
        return targets;
    }

    public Guest findById(Long id) {
        Guest guest = guestRepository.findOne(id);
        if (guest == null) {
            throw new IllegalArgumentException("해당 고객이 없습니다. id=" + id);
        }
        return guest;
    }
}