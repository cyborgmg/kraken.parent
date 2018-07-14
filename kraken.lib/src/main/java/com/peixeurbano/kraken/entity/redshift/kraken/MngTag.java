package com.peixeurbano.kraken.entity.redshift.kraken;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.peixeurbano.kraken.entity.abstracts.BtnDlgSelecaoQry;


/**
 * The persistent class for the mng_tags database table.
 * 
 */
@Entity
@Table(name="mng_tags", schema="kraken")
/*
@NamedQueries({
    @NamedQuery(name = MngTag.MNGTAG_FINDALL, query = MngTag.MNGTAG_BASE )
})
*/
public class MngTag extends BtnDlgSelecaoQry {
	private static final long serialVersionUID = 1L;
	
	public static final String  BASE = "select distinct t from MngTag t , MngDealsLatestTag d, MngDealsLatest dl, UseraccountUnifieddiscount u "
			+ "where dl.legacydataUnifiedDiscountId=u.unifieddiscountid and dl.dealId = d.dealId and d.tagDescription=t.slug";
	
	public static final String  MNGTAG_TAG_BASE = BASE+" and d.tagName = 'tags'";	
	
	public static final String  MNGTAG_TAG_QUERY = MngTag.MNGTAG_TAG_BASE+" and upper(t.name) ";
	public static final String  MNGTAG_TAG_IN = MngTag.MNGTAG_TAG_BASE+" and t.id in (";

	public static final String  MNGTAG_MARKETING_BASE = BASE+" and d.tagName = 'marketing'";
	
	public static final String  MNGTAG_MARKETING_QUERY = MngTag.MNGTAG_MARKETING_BASE+" and upper(t.name) ";
	public static final String  MNGTAG_MARKETING_IN = MngTag.MNGTAG_MARKETING_BASE+" and t.id in (";

	
	@Id
	@Column(name="id")
	private String id;

	@Column(name="name")
	private String name;

	@Column(name="parent")
	private String parent;

	@Column(name="phoenix_category")
	private String phoenixCategory;

	@Column(name="slug")
	private String slug;

	public MngTag() {
	}

	@Override
	public String getId() {
		return this.id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getParent() {
		return this.parent;
	}

	public void setParent(final String parent) {
		this.parent = parent;
	}

	public String getPhoenixCategory() {
		return this.phoenixCategory;
	}

	public void setPhoenixCategory(final String phoenixCategory) {
		this.phoenixCategory = phoenixCategory;
	}

	public String getSlug() {
		return this.slug;
	}

	public void setSlug(final String slug) {
		this.slug = slug;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public void setValue(final String nome) {

		this.name=nome;
		
	}

	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return null;
	}

}