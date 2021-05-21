package edu.axboot.domain.pms.chk;

import com.chequer.axboot.core.annotations.Comment;
import edu.axboot.domain.BaseJpaModel;
import edu.axboot.domain.pms.chkMemo.ChkMemo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


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

	public void setGuestId(long guestId){
		this.guestId = guestId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "RSV_NUM", referencedColumnName = "RSV_NUM", insertable = false, updatable = false)
	private List<ChkMemo> chkMemoList;

	@Builder
	public Chk(Long id, String rsvDt, Integer sno, String rsvNum, Long guestId, String guestNm, String guestNmEng, String guestTel, String email, String langCd, String arrDt, String arrTime, String depDt, String depTime, Integer nightCnt, String roomTypCd, String roomNum, Integer adultCnt, Integer chldCnt, String saleTypCd, String sttusCd, String srcCd, String brth, String gender, String payCd, String advnYn, BigDecimal salePrc, BigDecimal svcPrc) {
		this.id = id;
		this.rsvDt = rsvDt;
		this.sno = sno;
		this.rsvNum = rsvNum;
		this.guestId = guestId;
		this.guestNm = guestNm;
		this.guestNmEng = guestNmEng;
		this.guestTel = guestTel;
		this.email = email;
		this.langCd = langCd;
		this.arrDt = arrDt;
		this.arrTime = arrTime;
		this.depDt = depDt;
		this.depTime = depTime;
		this.nightCnt = nightCnt;
		this.roomTypCd = roomTypCd;
		this.roomNum = roomNum;
		this.adultCnt = adultCnt;
		this.chldCnt = chldCnt;
		this.saleTypCd = saleTypCd;
		this.sttusCd = sttusCd;
		this.srcCd = srcCd;
		this.brth = brth;
		this.gender = gender;
		this.payCd = payCd;
		this.advnYn = advnYn;
		this.salePrc = salePrc;
		this.svcPrc = svcPrc;
	}

	@Override
    public Long getId() {
        return id;
    }

	public void rsvNumGenerator(String rsvDt, int sno) {
		this.rsvDt = rsvDt;
		this.sno = sno;
		this.rsvNum = "R" + rsvDt.replaceAll("-", "") + StringUtils.leftPad(Integer.toString(sno), 3);
	}

}