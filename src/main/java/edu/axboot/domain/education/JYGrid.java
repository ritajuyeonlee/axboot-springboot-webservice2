package edu.axboot.domain.education;


import edu.axboot.domain.SimpleJpaModel;
import lombok.*;
import org.apache.ibatis.type.Alias;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import com.chequer.axboot.core.annotations.Comment;
import javax.persistence.*;
import java.io.Serializable;


@Setter
@Getter
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "EDUCATION_JY")

public class JYGrid extends SimpleJpaModel<Long> {

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


@Override
public Long getId() {
return id;
}


}