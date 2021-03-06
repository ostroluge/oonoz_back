package oonoz.domain;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The Class SubTheme.
 */
@Entity
@Table(name = "SUB_THEME")
public class SubTheme {

	/** The id. */
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	/** The id theme. */
	@Column(name = "ID_THEME")
	private long idTheme;

	/** The label. */
	@Column(unique = true, nullable = false, name = "LABEL")
	private String label;

	/** The description. */
	@Column(unique = false, nullable = false, name = "DESCRIPTION")
	private String description;

	/** The icon url. */
	@Column(unique = false, nullable = true, name = "ICON")
	private String iconUrl;

	/** The is validated. */
	@Column(unique = false, nullable = false, name="IS_VALID")
	private boolean isValidated;

	/** The list of qcm. */
	@OneToMany(fetch=FetchType.LAZY)
	@JoinTable(name="SUB_THEME_QCM",joinColumns = @JoinColumn(name="ID_SUB_THEME"),inverseJoinColumns = @JoinColumn(name = "ID_QCM"))
	private List<QCM> listQCMs;

	/**
	 * Gets the list of qcm.
	 *
	 * @return the list of qcm
	 */
	public List<QCM> getListQCMs() {
		return listQCMs;
	}

	/**
	 * Sets list of qcm.
	 *
	 * @param qCMs the new list of qcm
	 */
	public void setListQCMs(List<QCM> qCMs) {
		listQCMs = qCMs;
	}

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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SubTheme [id=" + id + ", idTheme=" + idTheme + ", label=" + label + ", description=" + description
				+ ", iconUrl=" + iconUrl + "]";
	}

	@Override
	public boolean equals(Object object)
	{
		if (object == null){
			return false;
		}
		if(object.getClass() != Long.class){
			return false;
		}
		
		Long idSubTheme= (Long)object;

		return idSubTheme==this.getId();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
