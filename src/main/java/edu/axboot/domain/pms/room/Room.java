package edu.axboot.domain.pms.room;


import com.chequer.axboot.core.annotations.Comment;
import edu.axboot.domain.BaseJpaModel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Entity
@Table(name = "PMS_ROOM")
public class Room extends BaseJpaModel<Long> {

	@Id
	@Column(name = "ID", precision = 19, nullable = false)
	@Comment(value = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "ROOM_NUM", length = 10, nullable = false)
	@Comment(value = "객실 번호")
	private String roomNum;

	@Column(name = "ROOM_TYP_CD", length = 20, nullable = false)
	@Comment(value = "객실 타입 CD")
	private String roomTypCd;

	@Column(name = "DND_YN", length = 1, nullable = false)
	@Comment(value = "DND 여부")
	private String dndYn;

	@Column(name = "EB_YN", length = 1, nullable = false)
	@Comment(value = "EB 여부")
	private String ebYn;

	@Column(name = "ROOM_STTUS_CD", length = 20)
	@Comment(value = "객실 상태 CD")
	private String roomSttusCd;

	@Column(name = "CLN_STTUS_CD", length = 20)
	@Comment(value = "청소 상태 CD")
	private String clnSttusCd;

	@Column(name = "SVC_STTUS_CD", length = 20)
	@Comment(value = "서비스 상태 CD")
	private String svcSttusCd;


	@Override
	public Long getId() {
	return id;
	}




}