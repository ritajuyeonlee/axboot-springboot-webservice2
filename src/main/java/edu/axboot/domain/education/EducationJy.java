package edu.axboot.domain.education;


import com.chequer.axboot.core.annotations.ColumnPosition;
import edu.axboot.domain.SimpleJpaModel;
import edu.axboot.domain.file.CommonFile;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import com.chequer.axboot.core.annotations.Comment;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "EDUCATION_JY")

public class EducationJy extends SimpleJpaModel<Long> {

	@Id
	@Column(name = "ID", precision = 19, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "COMPANY_NM", length = 30)
	@Comment(value = "")
	private String companyNm;

	@Column(name = "CEO", length = 30)
	@Comment(value = "")
	private String ceo;

	@Column(name = "BIZNO", length = 10)
	@Comment(value = "")
	private String bizno;

	@Column(name = "TEL", length = 18)
	@Comment(value = "")
	private String tel;

	@Column(name = "ZIP", length = 7)
	@Comment(value = "")
	private String zip;

	@Column(name = "ADDRESS", length = 200)
	@Comment(value = "")
	private String address;

	@Column(name = "ADDRESS_DETAIL", length = 200)
	@Comment(value = "")
	private String addressDetail;

	@Column(name = "EMAIL", length = 50)
	@Comment(value = "")
	private String email;

	@Column(name = "REMARK", length = 500)
	@Comment(value = "")
	private String remark;

	@Column(name = "USE_YN", length = 1)
	@Comment(value = "")
	private String useYn;

	@Column(name = "ATTACH_ID", length = 100)
	@ColumnPosition(12)
	private String attachId;

	@Transient
	private List<Long> fileIdList = new ArrayList<>();

	//1대N의 부모자식관계
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "TARGET_ID", referencedColumnName = "ATTACH_ID", insertable = false, updatable = false)
	private List<CommonFile> fileList;


@Override
public Long getId() {
return id;
}


}