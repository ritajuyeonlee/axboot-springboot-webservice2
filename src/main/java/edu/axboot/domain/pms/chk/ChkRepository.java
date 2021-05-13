package edu.axboot.domain.pms.chk;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChkRepository extends AXBootJPAQueryDSLRepository<Chk, Long> {
}
