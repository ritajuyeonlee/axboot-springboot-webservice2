package edu.axboot.domain.pms.chkMemo;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChkMemoRepository extends AXBootJPAQueryDSLRepository<ChkMemo, Long> {
}
