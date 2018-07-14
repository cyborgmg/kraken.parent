package com.peixeurbano.kraken.entity.sqlserver;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the CMP_COMPONENT database table.
 * 
 */
@Entity
@Table(name="CMP_COMPONENT")
@NamedQuery(name="CmpComponent.findAll", query="SELECT c FROM CmpComponent c")
public class CmpComponent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="COMPONENTID")
	private Integer componentid;

	@Column(name="ENABLED")
	private Boolean enabled;

	@Column(name="NAME")
	private String name;

	//bi-directional many-to-one association to CmpComponentConfig
	@OneToMany(mappedBy="cmpComponent")
	private List<CmpComponentConfig> cmpComponentConfigs;

	public CmpComponent() {
	}

	public Integer getComponentid() {
		return this.componentid;
	}

	public void setComponentid(final Integer componentid) {
		this.componentid = componentid;
	}

	public boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(final boolean enabled) {
		this.enabled = enabled;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public List<CmpComponentConfig> getCmpComponentConfigs() {
		return this.cmpComponentConfigs;
	}

	public void setCmpComponentConfigs(final List<CmpComponentConfig> cmpComponentConfigs) {
		this.cmpComponentConfigs = cmpComponentConfigs;
	}

	public CmpComponentConfig addCmpComponentConfig(final CmpComponentConfig cmpComponentConfig) {
		this.getCmpComponentConfigs().add(cmpComponentConfig);
		cmpComponentConfig.setCmpComponent(this);

		return cmpComponentConfig;
	}

	public CmpComponentConfig removeCmpComponentConfig(final CmpComponentConfig cmpComponentConfig) {
		this.getCmpComponentConfigs().remove(cmpComponentConfig);
		cmpComponentConfig.setCmpComponent(null);

		return cmpComponentConfig;
	}

}