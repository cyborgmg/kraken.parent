package com.peixeurbano.kraken.entity.sqlserver;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the AGERANGE database table.
 * 
 */
@Entity
@Table(name="AGERANGE")
@NamedQueries({
    @NamedQuery(name = Agerange.AGERANGE_FINDALL, query = "SELECT a FROM Agerange a")
})
public class Agerange implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String  AGERANGE_FINDALL = "Agerange.findAll";

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="AGE_ID")
	private Integer ageId;

	@Column(name="MAX_AGE")
	private Integer maxAge;

	@Column(name="MIN_AGE")
	private Integer minAge;

	//bi-directional many-to-one association to AgerangeSelect
	@OneToMany(mappedBy="agerange")
	private List<AgerangeSelect> agerangeSelects;

	public Agerange() {
	}

	public Integer getAgeId() {
		return this.ageId;
	}

	public void setAgeId(final Integer ageId) {
		this.ageId = ageId;
	}

	public Integer getMaxAge() {
		return this.maxAge;
	}

	public void setMaxAge(final Integer maxAge) {
		this.maxAge = maxAge;
	}

	public Integer getMinAge() {
		return this.minAge;
	}

	public void setMinAge(final Integer minAge) {
		this.minAge = minAge;
	}

	public List<AgerangeSelect> getAgerangeSelects() {
		return this.agerangeSelects;
	}

	public void setAgerangeSelects(final List<AgerangeSelect> agerangeSelects) {
		this.agerangeSelects = agerangeSelects;
	}

	public AgerangeSelect addAgerangeSelect(final AgerangeSelect agerangeSelect) {
		this.getAgerangeSelects().add(agerangeSelect);
		agerangeSelect.setAgerange(this);

		return agerangeSelect;
	}

	public AgerangeSelect removeAgerangeSelect(final AgerangeSelect agerangeSelect) {
		this.getAgerangeSelects().remove(agerangeSelect);
		agerangeSelect.setAgerange(null);

		return agerangeSelect;
	}

}