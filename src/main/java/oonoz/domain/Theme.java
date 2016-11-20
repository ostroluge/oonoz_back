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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The Class Theme.
 */
@Entity
@Table(name = "THEME")
public class Theme {

	/** The id theme. */
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idTheme;

	/** The label. */
	@Column(unique = true, nullable = false, name="LABEL")
	private String label;
	
	/** The description. */
	@Column(unique = false, nullable = false, name="DESCRIPTION")
	private String description;
	
	/** The icon url. */
	@Column(unique = false, nullable = true, name="ICON")
	private String iconUrl;

	/** The sub themes. */
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
	@JoinColumn(name = "ID_THEME")
	private List<SubTheme> subThemes;
	
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
	/**
	 * @param iconUrl
	 */
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
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

	@Override
	public String toString() {
		return "Theme [idTheme=" + idTheme + ", label=" + label + ", description=" + description + ", iconUrl="
				+ iconUrl + ", subThemes=" + subThemes + "]";
	}
}
