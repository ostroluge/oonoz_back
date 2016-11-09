package oonoz.dto.model;

/**
 * The Class ThemeDto.
 */
public class ThemeDto {

	/** The id theme. */
	private long idTheme;
	
	/** The label. */
	private String label;
	
	/** The description. */
	private String description;
	
	/** The icon url. */
	private String iconUrl;

	/**
	 * Gets the label.
	 *
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Sets the label.
	 *
	 * @param label the new label
	 */
	public void setLabel(String label) {
		this.label = label;
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
	 * Gets the icon url.
	 *
	 * @return the icon url
	 */
	public String getIconUrl() {
		return iconUrl;
	}

	/**
	 * Sets the icon url.
	 *
	 * @param iconUrl the new icon url
	 */
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
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
}
