package oonoz.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The Class Authorities.
 * 
 * Description :
 */
@Entity
@Table(name="AUTHORITIES")
public class Authorities {

	/** The id authorities. */
	@Id
	@Column(name="ID_AUTHORITIES")
	private long idAuthorities;
	
	/** The username. */
	@Column(name="USERNAME")
	private String username;
	
	/** The role. */
	@Column(name="ROLE")
	private String role;
	

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the role.
	 *
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Sets the role.
	 *
	 * @param role the new role
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * Gets the id authorities.
	 *
	 * @return the id authorities
	 */
	public long getIdAuthorities() {
		return idAuthorities;
	}

	/**
	 * Sets the id authorities.
	 *
	 * @param idAuthorities the new id authorities
	 */
	public void setIdAuthorities(long idAuthorities) {
		this.idAuthorities = idAuthorities;
	}
	
}
