package oonoz.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

// TODO: Auto-generated Javadoc
/**
 * The Class QCM.
 */
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
	
	/** The theme. */
	@OneToOne
	@JoinColumn(name="ID_THEME",insertable = false,updatable = false)
	private Theme theme;
	
	/** The id supplier. */
	@Column(unique = true, nullable = false,name="ID_SUPPLIER")
	private long idSupplier;
	
	/** The theme. */
	@OneToOne
	@JoinColumn(name="ID_SUPPLIER",insertable = false,updatable = false)
	private Supplier supplier;
	
	/** The sub themes. */
	@OneToMany
	@LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name="SUB_THEME_QCM",joinColumns =@JoinColumn(name = "ID_QCM") ,inverseJoinColumns = @JoinColumn(name="ID_SUB_THEME"))
	private List<SubTheme> subThemes;
	
	/** The name. */
	@Column(unique = true, nullable = false,name="NAME")
	private String name;
	
	/** The description. */
	@Column(unique = false, nullable = false,name="DESCRIPTION")
	private String description;
	
	/** The is validated. */
	@Column(unique = false, nullable = false,name="IS_VALIDATED")
	private boolean isValidated;
	
	/** The is free. */
	@Column(unique = false, nullable = false,name="IS_FREE")
	private boolean isFree;
	
	/** The price. */
	@Column(unique = false, nullable = true,name="PRICE")
	private float price;
	
	/** The icon. */
	@Column(unique = false, nullable = true,name="ICON")
	private String icon;
	
	/** The prize name. */
	@Column(unique = false, nullable = true,name="PRIZE_NAME")
	private String prizeName;
	
	/** The prize description. */
	@Column(unique = false, nullable = true,name="PRIZE_DESCRIPTION")
	private String prizeDescription;
	
	/** The minimal score. */
	@Column(name="MINIMAL_SCORE")
	private int minimalScore;
	
	/** The category. */
	@Column(unique = false, nullable = false,name="CATEGORY")
	private String category;
	
	/** The questions. */
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany( cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_QCM",updatable = false)
	private List<Question> questions;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the theme.
	 *
	 * @return the theme
	 */
	public Theme getTheme() {
		return theme;
	}

	/**
	 * Sets the theme.
	 *
	 * @param theme the new theme
	 */
	public void setTheme(Theme theme) {
		this.theme = theme;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Checks if is validated.
	 *
	 * @return true, if is validated
	 */
	public boolean isValidated() {
		return isValidated;
	}

	/**
	 * Sets the validated.
	 *
	 * @param isValidated the new validated
	 */
	public void setValidated(boolean isValidated) {
		this.isValidated = isValidated;
	}

	/**
	 * Checks if is free.
	 *
	 * @return true, if is free
	 */
	public boolean isFree() {
		return isFree;
	}

	/**
	 * Sets the free.
	 *
	 * @param isFree the new free
	 */
	public void setFree(boolean isFree) {
		this.isFree = isFree;
	}

	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * Sets the price.
	 *
	 * @param price the new price
	 */
	public void setPrice(float price) {
		this.price = price;
	}

	/**
	 * Gets the icon.
	 *
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * Sets the icon.
	 *
	 * @param icon the new icon
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * Gets the prize name.
	 *
	 * @return the prize name
	 */
	public String getPrizeName() {
		return prizeName;
	}

	/**
	 * Sets the prize name.
	 *
	 * @param prizeName the new prize name
	 */
	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}

	/**
	 * Gets the prize description.
	 *
	 * @return the prize description
	 */
	public String getPrizeDescription() {
		return prizeDescription;
	}

	/**
	 * Sets the prize description.
	 *
	 * @param prizeDescription the new prize description
	 */
	public void setPrizeDescription(String prizeDescription) {
		this.prizeDescription = prizeDescription;
	}

	/**
	 * Gets the minimal score.
	 *
	 * @return the minimal score
	 */
	public int getMinimalScore() {
		return minimalScore;
	}

	/**
	 * Sets the minimal score.
	 *
	 * @param minimalScore the new minimal score
	 */
	public void setMinimalScore(int minimalScore) {
		this.minimalScore = minimalScore;
	}

	/**
	 * Gets the category.
	 *
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Sets the category.
	 *
	 * @param category the new category
	 */
	public void setCategory(String category) {
		this.category = category;
	}


	/**
	 * Gets the id theme.
	 *
	 * @return the id theme
	 */
	public long getIdTheme() {
		return idTheme;
	}

	/**
	 * Sets the id theme.
	 *
	 * @param idTheme the new id theme
	 */
	public void setIdTheme(long idTheme) {
		this.idTheme = idTheme;
	}

	/**
	 * Gets the id supplier.
	 *
	 * @return the id supplier
	 */
	public long getIdSupplier() {
		return idSupplier;
	}

	/**
	 * Sets the id supplier.
	 *
	 * @param idSupplier the new id supplier
	 */
	public void setIdSupplier(long idSupplier) {
		this.idSupplier = idSupplier;
	}

	/**
	 * Gets the sub themes.
	 *
	 * @return the sub themes
	 */
	public List<SubTheme> getSubThemes() {
		return subThemes;
	}

	/**
	 * Sets the sub themes.
	 *
	 * @param subThemes the new sub themes
	 */
	public void setSubThemes(List<SubTheme> subThemes) {
		this.subThemes = subThemes;
	}

	/**
	 * Gets the questions.
	 *
	 * @return the questions
	 */
	public List<Question> getQuestions() {
		return questions;
	}

	/**
	 * Sets the questions.
	 *
	 * @param questions the new questions
	 */
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	
	
}
