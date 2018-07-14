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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the [USER] database table.
 * 
 */
@Entity
@Table(name="[USER]")
@NamedQueries({
@NamedQuery(name=User.USER_BY_MAIL, query="SELECT u FROM User u where u.mail = :mail "),
@NamedQuery(name=User.USER_GET_MAILS_CC, query="SELECT u.mail FROM User u, UserRole ur, Role ro, RightRole rr, Right ri WHERE "
		+ "u.userId = ur.user.userId AND ur.role.roleId = ro.roleId AND ro.roleId = rr.role.roleId "
		+ "AND rr.right.rightId = ri.rightId AND ri.right = 2")
})
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String USER_BY_MAIL = "user.by.mail"; 
	public static final String USER_GET_MAILS_CC = "user.get.mails.cc"; 
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="USER_ID")
	private Integer userId;

	@Column(name="MAIL")
	private String mail;
	
	@Column(name="ACTIVE")
	private Boolean active = Boolean.FALSE;

	//bi-directional many-to-many association to Role
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(
		name="USER_ROLE"
		, joinColumns={
			@JoinColumn(name="USER_ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="ROLE_ID")
			}
		)
	private List<Role> roles;

	//bi-directional many-to-one association to UserRole
	@OneToMany(mappedBy="user")
	private List<UserRole> userRoles;
	
	//bi-directional many-to-one association to Campaign
	@OneToMany(mappedBy="userIdInsert")
	private List<Campaign> campaignsInsert;

	//bi-directional many-to-one association to Campaign
	@OneToMany(mappedBy="userIdUpdate")
	private List<Campaign> campaignsUpdate;

	public User() {
	}
	
	

	public User(Integer userId, String mail) {
		super();
		this.userId = userId;
		this.mail = mail;
	}



	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(final Integer userId) {
		this.userId = userId;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(final String mail) {
		this.mail = mail;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(final List<Role> roles) {
		this.roles = roles;
	}

	public List<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(final List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public UserRole addUserRole(final UserRole userRole) {
		this.getUserRoles().add(userRole);
		userRole.setUser(this);

		return userRole;
	}

	public UserRole removeUserRole(final UserRole userRole) {
		this.getUserRoles().remove(userRole);
		userRole.setUser(null);

		return userRole;
	}

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(final Boolean active) {
		this.active = active;
	}
	public void setCampaignsInsert(final List<Campaign> campaignsInsert) {
		this.campaignsInsert = campaignsInsert;
	}

	public List<Campaign> getCampaignsInsert() {
		return this.campaignsInsert;
	}
	
	public Campaign addCampaignsInsert(final Campaign campaignsInsert) {
		this.getCampaignsInsert().add(campaignsInsert);
		campaignsInsert.setUserIdInsert(this);

		return campaignsInsert;
	}

	public Campaign removeCampaignsInsert(final Campaign campaignsInsert) {
		this.getCampaignsInsert().remove(campaignsInsert);
		campaignsInsert.setUserIdInsert(null);

		return campaignsInsert;
	}

	public List<Campaign> getCampaignsUpdate() {
		return this.campaignsUpdate;
	}

	public void setCampaignsUpdate(final List<Campaign> campaignsUpdate) {
		this.campaignsUpdate = campaignsUpdate;
	}

	public Campaign addCampaignsUpdate(final Campaign campaignsUpdate) {
		this.getCampaignsUpdate().add(campaignsUpdate);
		campaignsUpdate.setUserIdUpdate(this);

		return campaignsUpdate;
	}

	public Campaign removeCampaignsUpdate(final Campaign campaignsUpdate) {
		this.getCampaignsUpdate().remove(campaignsUpdate);
		campaignsUpdate.setUserIdUpdate(null);

		return campaignsUpdate;
	}

	
}