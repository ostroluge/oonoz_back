package oonoz.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="AUTHORITIES")
public class Authorities {

	@Id
	@Column(name="ID_AUTHORITIES")
	private long idAuthorities;
	
	@Column(name="USERNAME")
	private String username;
	
	@Column(name="ROLE")
	private String role;
	
	/*@MapsId
	@OneToOne(mappedBy = "authorities")
    @JoinColumn(name = "ID_AUTHORITIES")
	private Player player;*/


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public long getIdAuthorities() {
		return idAuthorities;
	}

	public void setIdAuthorities(long idAuthorities) {
		this.idAuthorities = idAuthorities;
	}

	/*public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}*/
	
	
}
