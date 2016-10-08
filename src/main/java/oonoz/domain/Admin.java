package oonoz.domain;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * The Class Admin.
 * 
 * Description :
 */
@Entity
@Table(name="ADMIN")
@PrimaryKeyJoinColumn(name="ID_PLAYER")
public class Admin extends Player{

}
