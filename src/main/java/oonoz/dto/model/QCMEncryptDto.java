package oonoz.dto.model;

import java.util.List;


import oonoz.domain.SubTheme;

public class QCMEncryptDto {
	
	/** The id. */
	private long id;
	
	/** The id theme. */
	private long idTheme;
	
	/** The theme. */
	private String themeName;
	
	/** The id supplier. */
	private long idSupplier;
	
	/** The supplier name. */
	private String supplierName;
	
	/** The name. */
	private String name;
	
	/** The description. */
	private String description;
		
	/** The icon. */
	private String icon;
	
	/** The prize name. */
	private String prizeName;
	
	/** The prize description. */
	private String prizeDescription;
	
	/** The minimal score. */
	private int minimalScore;
	
	/** The category. */
	private String category;
	
	/** The questions. */
	private List<QuestionEncryptDto> questions;
	
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

	


	public List<QuestionEncryptDto> getQuestions() {
		return questions;
	}

	public void setQuestions(List<QuestionEncryptDto> questions) {
		this.questions = questions;
	}

	/**
	 * Gets the theme name.
	 *
	 * @return the theme name
	 */
	public String getThemeName() {
		return themeName;
	}

	/**
	 * Sets the theme name.
	 *
	 * @param themeName the new theme name
	 */
	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}

	/**
	 * Gets the supplier name.
	 *
	 * @return the supplier name
	 */
	public String getSupplierName() {
		return supplierName;
	}

	/**
	 * Sets the supplier name.
	 *
	 * @param supplierName the new supplier name
	 */
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	


}
