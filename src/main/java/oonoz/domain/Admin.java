package oonoz.back.domain;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="ADMIN")
@PrimaryKeyJoinColumn(name="ID_PLAYER")
public class Admin extends Player{

}
