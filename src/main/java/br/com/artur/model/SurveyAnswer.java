package br.com.artur.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Email;

@Entity
public class SurveyAnswer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private Integer answer1;
	
	@Column
	private Integer answer2;
	
	@Column
	private Integer answer3;
	
	@Email
	@Column
	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAnswer1() {
		return answer1;
	}

	public void setAnswer1(Integer answer1) {
		this.answer1 = answer1;
	}

	public Integer getAnswer2() {
		return answer2;
	}

	public void setAnswer2(Integer answer2) {
		this.answer2 = answer2;
	}

	public Integer getAnswer3() {
		return answer3;
	}

	public void setAnswer3(Integer answer3) {
		this.answer3 = answer3;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SurveyAnswer other = (SurveyAnswer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
