package com.peixeurbano.kraken.entity.sqlserver;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.peixeurbano.kraken.entity.enums.RightEnum;


/**
 * The persistent class for the [RIGHT] database table.
 * 
 */
@Entity
@Table(name="[RIGHT]")
@NamedQuery(name="Right.findAll", query="SELECT r FROM Right r")
public class Right implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="RIGHT_ID")
	private Integer rightId;

	@Column(name="RIGHT_DS")
	private String rightDs;
	
	@Column(name="RIGHT_ENUM")
	private RightEnum rightEnum;
	
	@Column(name="RIGHT_ENUM", updatable=false, insertable=false)
	private Integer right;

	//bi-directional many-to-one association to RightRole
	@OneToMany(mappedBy="right")
	private List<RightRole> rightRoles;

	//bi-directional many-to-many association to Role
	@ManyToMany(mappedBy="rights")
	private List<Role> roles;

	public Right() {
	}

	public Integer getRightId() {
		return this.rightId;
	}

	public void setRightId(final Integer rightId) {
		this.rightId = rightId;
	}

	public String getRightDs() {
		return this.rightDs;
	}

	public void setRightDs(final String rightDs) {
		this.rightDs = rightDs;
	}

	public List<RightRole> getRightRoles() {
		return this.rightRoles;
	}

	public void setRightRoles(final List<RightRole> rightRoles) {
		this.rightRoles = rightRoles;
	}

	public RightRole addRightRole(final RightRole rightRole) {
		this.getRightRoles().add(rightRole);
		rightRole.setRight(this);

		return rightRole;
	}

	public RightRole removeRightRole(final RightRole rightRole) {
		this.getRightRoles().remove(rightRole);
		rightRole.setRight(null);

		return rightRole;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(final List<Role> roles) {
		this.roles = roles;
	}

	public RightEnum getRightEnum() {
		return this.rightEnum;
	}

	public void setRightEnum(final RightEnum rightEnum) {
		this.rightEnum = rightEnum;
	}

	public Integer getRight() {
		return this.right;
	}

	public void setRight(final Integer right) {
		this.right = right;
	}

}