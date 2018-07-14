package com.peixeurbano.kraken.entity.sqlserver;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the [ROLE] database table.
 * 
 */
@Entity
@Table(name="[ROLE]")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ROLE_ID")
	private Integer roleId;

	@Column(name="ROLE_DS")
	private String roleDs;

	//bi-directional many-to-one association to RightRole
	@OneToMany(mappedBy="role")
	private List<RightRole> rightRoles;

	//bi-directional many-to-many association to Right
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(
		name="RIGHT_ROLE"
		, joinColumns={
			@JoinColumn(name="ROLE_ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="RIGHT_ID")
			}
		)
	private List<Right> rights;

	//bi-directional many-to-many association to User
	@ManyToMany(mappedBy="roles")
	private List<User> users;

	//bi-directional many-to-one association to UserRole
	@OneToMany(mappedBy="role")
	private List<UserRole> userRoles;

	public Role() {
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(final Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleDs() {
		return this.roleDs;
	}

	public void setRoleDs(final String roleDs) {
		this.roleDs = roleDs;
	}

	public List<RightRole> getRightRoles() {
		return this.rightRoles;
	}

	public void setRightRoles(final List<RightRole> rightRoles) {
		this.rightRoles = rightRoles;
	}

	public RightRole addRightRole(final RightRole rightRole) {
		this.getRightRoles().add(rightRole);
		rightRole.setRole(this);

		return rightRole;
	}

	public RightRole removeRightRole(final RightRole rightRole) {
		this.getRightRoles().remove(rightRole);
		rightRole.setRole(null);

		return rightRole;
	}

	public List<Right> getRights() {
		return this.rights;
	}

	public void setRights(final List<Right> rights) {
		this.rights = rights;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(final List<User> users) {
		this.users = users;
	}

	public List<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(final List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public UserRole addUserRole(final UserRole userRole) {
		this.getUserRoles().add(userRole);
		userRole.setRole(this);

		return userRole;
	}

	public UserRole removeUserRole(final UserRole userRole) {
		this.getUserRoles().remove(userRole);
		userRole.setRole(null);

		return userRole;
	}

}