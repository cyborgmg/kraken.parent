package com.peixeurbano.kraken.entity.sqlserver;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.peixeurbano.kraken.entity.abstracts.BtnDlgSelecaoQry;


/**
 * The persistent class for the SUBCATEGORY_SELECT database table.
 * 
 */
@Entity
@Table(name="SUBCATEGORY_SELECT")
@NamedQuery(name="SubcategorySelect.findAll", query="SELECT s FROM SubcategorySelect s")
public class SubcategorySelect extends BtnDlgSelecaoQry {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="SUBCATEGORY_SELECT_ID")
	private Integer subcategorySelectId;

	@Column(name="SOURCE_ID")
	private String sourceId;

	//bi-directional many-to-one association to Campaign
	@ManyToOne
	@JoinColumn(name="CAMPAIGN_ID")
	private Campaign campaign;
	
	@Transient
	private String nome;

	public SubcategorySelect() {
	}
	
	public SubcategorySelect(final Integer subcategorySelectId, final Campaign campaign, final String sourceId, final String nome) {
		super();
		this.subcategorySelectId = subcategorySelectId;
		this.campaign = campaign;
		this.sourceId = sourceId;
		this.nome=nome;
	}


	public Integer getSubcategorySelectId() {
		return this.subcategorySelectId;
	}

	public void setSubcategorySelectId(final Integer subcategorySelectId) {
		this.subcategorySelectId = subcategorySelectId;
	}

	public String getSourceId() {
		return this.sourceId;
	}

	public void setSourceId(final String sourceId) {
		this.sourceId = sourceId;
	}

	public Campaign getCampaign() {
		return this.campaign;
	}

	public void setCampaign(final Campaign campaign) {
		this.campaign = campaign;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(final String nome) {
		this.nome = nome;
	}

	@Override
	public String getId() {
		return this.sourceId.toString();
	}

	@Override
	public String getValue() {
		return this.nome;
	}

	@Override
	public String getCode() {
		return null;
	}

	@Override
	public void setValue(final String nome) {
		this.nome = nome;
	}

}