package com.peixeurbano.kraken.entity.sqlserver;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the RIGHT_ROLE database table.
 * 
 */
@Entity
@Table(name="RIGHT_ROLE")
@NamedQuery(name="RightRole.findAll", query="SELECT r FROM RightRole r")
public class RightRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="RIGHT_ROLE_ID")
	private Integer rightRoleId;

	//bi-directional many-to-one association to Right
	@ManyToOne
	@JoinColumn(name="RIGHT_ID")
	private Right right;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="ROLE_ID")
	private Role role;

	public RightRole() {
	}

	public Integer getRightRoleId() {
		return this.rightRoleId;
	}

	public void setRightRoleId(final Integer rightRoleId) {
		this.rightRoleId = rightRoleId;
	}

	public Right getRight() {
		return this.right;
	}

	public void setRight(final Right right) {
		this.right = right;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(final Role role) {
		this.role = role;
	}

}