package com.peixeurbano.kraken.entity.sqlserver;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.peixeurbano.kraken.entity.enums.CmpEnum;


/**
 * The persistent class for the CMP_COMPONENT_CONFIG database table.
 * 
 */
@Entity
@Table(name="CMP_COMPONENT_CONFIG")
@NamedQueries({
@NamedQuery(name=CmpComponentConfig.CMPCOMPONENTCONFIG_FINDALL, query="SELECT c FROM CmpComponentConfig c")
})
public class CmpComponentConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String CMPCOMPONENTCONFIG_FINDALL = "CmpComponentConfig.findAll"; 
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CMP_COMPONENT_CONFIG_ID")
	private Integer cmpComponentConfigId;

	@Column(name="NAME")
	private String name;

	@Column(name="[VALUE]")
	private String value;
	
	@Column(name="ENUM")
	private CmpEnum cmpEnum;

	//bi-directional many-to-one association to CmpComponent
	@ManyToOne
	@JoinColumn(name="COMPONENTID")
	private CmpComponent cmpComponent;

	public CmpComponentConfig() {
	}

	public Integer getCmpComponentConfigId() {
		return this.cmpComponentConfigId;
	}

	public void setCmpComponentConfigId(final Integer cmpComponentConfigId) {
		this.cmpComponentConfigId = cmpComponentConfigId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(final String value) {
		this.value = value;
	}

	public CmpComponent getCmpComponent() {
		return this.cmpComponent;
	}

	public void setCmpComponent(final CmpComponent cmpComponent) {
		this.cmpComponent = cmpComponent;
	}

	public CmpEnum getCmpEnum() {
		return this.cmpEnum;
	}

	public void setCmpEnum(final CmpEnum cmpEnum) {
		this.cmpEnum = cmpEnum;
	}

}