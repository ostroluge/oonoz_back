package oonoz.domain;

/**
 * The Class Comment.
 */
public class Comment {

	/** The value. */
	private String value;
	
	/** The author. */
	private String author;

	/**
	 * Instantiates a new comment.
	 *
	 * @param value the value
	 * @param author the author
	 */
	public Comment(String value, String author) {
		this.value = value;
		this.author = author;
	}
	
	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Gets the author.
	 *
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Sets the author.
	 *
	 * @param author the new author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
}
