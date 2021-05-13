package edu.axboot.domain.pms.chk;

import com.chequer.axboot.core.annotations.Comment;
import edu.axboot.domain.BaseJpaModel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;


@Getter
@NoArgsConstructor
@Entity
@Table(name = "PMS_CHK")

public class Chk extends BaseJpaModel<Long> {

	@Id
	@Column(name = "ID", precision = 19, nullable = false)
	@Comment(value = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "RSV_DT", length = 10, nullable = false)
	@Comment(value = "예약 일자")
	private String rsvDt;

	@Column(name = "SNO", precision = 10, nullable = false)
	@Comment(value = "일련번호")
	private Integer sno;

	@Column(name = "RSV_NUM", length = 20, nullable = false)
	@Comment(value = "예약 번호")
	private String rsvNum;

	@Column(name = "GUEST_ID", precision = 19)
	@Comment(value = "투숙객 ID")
	private Long guestId;

	@Column(name = "GUEST_NM", length = 100)
	@Comment(value = "투숙객 명")
	private String guestNm;

	@Column(name = "GUEST_NM_ENG", length = 200)
	@Comment(value = "투숙객 명 영어")
	private String guestNmEng;

	@Column(name = "GUEST_TEL", length = 18)
	@Comment(value = "투숙객 전화")
	private String guestTel;

	@Column(name = "EMAIL", length = 100)
	@Comment(value = "이메일")
	private String email;

	@Column(name = "LANG_CD", length = 20)
	@Comment(value = "언어 CD")
	private String langCd;

	@Column(name = "ARR_DT", length = 10, nullable = false)
	@Comment(value = "도착 일자")
	private String arrDt;

	@Column(name = "ARR_TIME", length = 8)
	@Comment(value = "도착 시간")
	private String arrTime;

	@Column(name = "DEP_DT", length = 10, nullable = false)
	@Comment(value = "출발 일자")
	private String depDt;

	@Column(name = "DEP_TIME", length = 8)
	@Comment(value = "출발 시간")
	private String depTime;

	@Column(name = "NIGHT_CNT", precision = 10, nullable = false)
	@Comment(value = "숙박 수")
	private Integer nightCnt;

	@Column(name = "ROOM_TYP_CD", length = 20, nullable = false)
	@Comment(value = "객실 타입 CD")
	private String roomTypCd;

	@Column(name = "ROOM_NUM", length = 10)
	@Comment(value = "객실 번호")
	private String roomNum;

	@Column(name = "ADULT_CNT", precision = 10, nullable = false)
	@Comment(value = "성인 수")
	private Integer adultCnt;

	@Column(name = "CHLD_CNT", precision = 10, nullable = false)
	@Comment(value = "아동 수")
	private Integer chldCnt;

	@Column(name = "SALE_TYP_CD", length = 20, nullable = false)
	@Comment(value = "판매 유형 CD")
	private String saleTypCd;

	@Column(name = "STTUS_CD", length = 20, nullable = false)
	@Comment(value = "상태 CD")
	private String sttusCd;

	@Column(name = "SRC_CD", length = 20, nullable = false)
	@Comment(value = "소스 CD")
	private String srcCd;

	@Column(name = "BRTH", length = 10)
	@Comment(value = "생일")
	private String brth;

	@Column(name = "GENDER", length = 20)
	@Comment(value = "성별")
	private String gender;

	@Column(name = "PAY_CD", length = 20)
	@Comment(value = "결제 CD")
	private String payCd;

	@Column(name = "ADVN_YN", length = 1, nullable = false)
	@Comment(value = "선수금 여부")
	private String advnYn;

	@Column(name = "SALE_PRC", precision = 18, scale = 0)
	@Comment(value = "판매 가격")
	private BigDecimal salePrc;

	@Column(name = "SVC_PRC", precision = 18, scale = 0)
	@Comment(value = "서비스 가격")
	private BigDecimal svcPrc;


    @Override
    public Long getId() {
        return id;
    }
}