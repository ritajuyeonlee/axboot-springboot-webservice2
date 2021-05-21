package edu.axboot.domain.pms.chkMemo;

import com.chequer.axboot.core.annotations.Comment;
import com.fasterxml.jackson.annotation.JsonFormat;
import edu.axboot.domain.BaseJpaModel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "PMS_CHK_MEMO")

public class ChkMemo extends BaseJpaModel<Long> {

	@Id
	@Column(name = "ID", precision = 19, nullable = false)
	@Comment(value = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "RSV_NUM", length = 20)
	@Comment(value = "예약번호")
	private String rsvNum;

	@Column(name = "SNO", precision = 10, nullable = false)
	@Comment(value = "일련번호")
	private Integer sno;

	@Column(name = "MEMO_CN", length = 4000, nullable = false)
	@Comment(value = "메모 내용")
	private String memoCn;

	@JsonFormat(pattern = "yyyy-MM-dd hh:mm")
	@Column(name = "MEMO_DTTI", nullable = false)
	@Comment(value = "메모 일시")
	private Timestamp memoDtti;

	@Column(name = "MEMO_MAN", length = 100, nullable = false)
	@Comment(value = "메모 자")
	private String memoMan;

	@Column(name = "DEL_YN", length = 1, nullable = false)
	@Comment(value = "삭제 여부")
	private String delYn;

	@Builder
	public ChkMemo(Long id, String rsvNum, Integer sno, String memoCn, Timestamp memoDtti, String memoMan, String delYn) {
		this.id = id;
		this.rsvNum = rsvNum;
		this.sno = sno;
		this.memoCn = memoCn;
		this.memoDtti = memoDtti;
		this.memoMan = memoMan;
		this.delYn = delYn;
	}

	@Override
    public Long getId() {
        return id;
    }
}