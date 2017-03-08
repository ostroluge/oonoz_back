package oonoz.util;

/**
 * The Class QCMFilteredSearch.
 * 
 * Description :
 */
public class QCMFilteredSearch {
	
	/** The label search. */
	private String labelSearch;
	
	/** The id theme. */
	private Long idTheme;
	
	/** The id subtheme. */
	private Long idSubTheme;
	
	/** The is free. */
	private Boolean isFree;
	
	/** The has gift. */
	private Boolean hasGift;
	
	/** The max price. */
	private Float maxPrice;
	
	
	/** The category. */
	private String category;

	/**
	 * Gets the label search.
	 *
	 * @return the label search
	 */
	public String getLabelSearch() {
		return labelSearch;
	}

	/**
	 * Sets the label search.
	 *
	 * @param labelSearch the new label search
	 */
	public void setLabelSearch(String labelSearch) {
		this.labelSearch = labelSearch;
	}

	/**
	 * Checks if is free.
	 *
	 * @return true, if is free
	 */
	public Boolean isFree() {
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
	 * Checks if is checks for gift.
	 *
	 * @return true, if is checks for gift
	 */
	public Boolean isHasGift() {
		return hasGift;
	}

	/**
	 * Sets the checks for gift.
	 *
	 * @param hasGift the new checks for gift
	 */
	public void setHasGift(boolean hasGift) {
		this.hasGift = hasGift;
	}

	

	/**
	 * Gets the id theme.
	 *
	 * @return the id theme
	 */
	public Long getIdTheme() {
		return idTheme;
	}

	/**
	 * Sets the id theme.
	 *
	 * @param idTheme the new id theme
	 */
	public void setIdTheme(Long idTheme) {
		this.idTheme = idTheme;
	}

	public Long getIdSubTheme() {
		return idSubTheme;
	}

	public void setIdSubTheme(Long idSubTheme) {
		this.idSubTheme = idSubTheme;
	}

	/**
	 * Gets the max price.
	 *
	 * @return the max price
	 */
	public Float getMaxPrice() {
		return maxPrice;
	}

	/**
	 * Sets the max price.
	 *
	 * @param maxPrice the new max price
	 */
	public void setMaxPrice(float maxPrice) {
		this.maxPrice = maxPrice;
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
	
	

}
