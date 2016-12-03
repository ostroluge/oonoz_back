package oonoz.domain;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;



@Entity
@Table(name="QCM")
public class QCM {
	
	/** The id QCM. */
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	/** The name. */
	@Column(unique = true, nullable = false,name="ID_THEME")
	private long idTheme;
	
	@OneToOne
	@JoinColumn(name="ID_THEME",insertable = false,updatable = false)
	private Theme theme;
	
	@Column(unique = true, nullable = false,name="ID_SUPPLIER")
	private long idSupplier;
	
	
	@OneToMany
	@LazyCollection(LazyCollectionOption.TRUE)
    @JoinTable(name="SUB_THEME_QCM",joinColumns =@JoinColumn(name = "ID_QCM") ,inverseJoinColumns = @JoinColumn(name="ID_SUB_THEME"))
	private List<SubTheme> subThemes;
	
	/** The name. */
	@Column(unique = true, nullable = false,name="NAME")
	private String name;
	
	/** The description. */
	@Column(unique = false, nullable = false,name="DESCRIPTION")
	private String description;
	
	
	@Column(unique = false, nullable = false,name="IS_VALIDATED")
	private boolean isValidated;
	
	
	@Column(unique = false, nullable = false,name="IS_FREE")
	private boolean isFree;
	
	@Column(unique = false, nullable = true,name="PRICE")
	private float price;
	
	@Column(unique = false, nullable = true,name="ICON")
	private String icon;
	
	@Column(unique = false, nullable = true,name="PRIZE_NAME")
	private String prizeName;
	
	@Column(unique = false, nullable = true,name="PRIZE_DESCRIPTION")
	private String prizeDescription;
	
	@Column(name="MINIMAL_SCORE")
	private int minimalScore;
	
	@Column(unique = false, nullable = false,name="CATEGORY")
	private String category;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany( cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_QCM",updatable = false)
	private List<Question> questions;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isValidated() {
		return isValidated;
	}

	public void setValidated(boolean isValidated) {
		this.isValidated = isValidated;
	}

	public boolean isFree() {
		return isFree;
	}

	public void setFree(boolean isFree) {
		this.isFree = isFree;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getPrizeName() {
		return prizeName;
	}

	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}

	public String getPrizeDescription() {
		return prizeDescription;
	}

	public void setPrizeDescription(String prizeDescription) {
		this.prizeDescription = prizeDescription;
	}

	public int getMinimalScore() {
		return minimalScore;
	}

	public void setMinimalScore(int minimalScore) {
		this.minimalScore = minimalScore;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}


	public long getIdTheme() {
		return idTheme;
	}

	public void setIdTheme(long idTheme) {
		this.idTheme = idTheme;
	}

	public long getIdSupplier() {
		return idSupplier;
	}

	public void setIdSupplier(long idSupplier) {
		this.idSupplier = idSupplier;
	}

	public List<SubTheme> getSubThemes() {
		return subThemes;
	}

	public void setSubThemes(List<SubTheme> subThemes) {
		this.subThemes = subThemes;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
	

}
