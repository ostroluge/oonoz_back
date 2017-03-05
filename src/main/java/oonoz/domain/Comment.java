package oonoz.domain;

/**
 * The Class Comment.
 */
public class Comment {

	/** The value. */
	private String value;
	
	/** The author. */
	private String author;

	/** The rating. */
	private Double rating;
	
	/**
	 * Instantiates a new comment.
	 *
	 * @param value the value
	 * @param author the author
	 * @param rating the rating
	 */
	public Comment(String value, String author, Double rating) {
		this.value = value;
		this.author = author;
		this.rating = rating;
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

	/**
	 * Gets the rating.
	 *
	 * @return the rating
	 */
	public Double getRating() {
		return rating;
	}

	/**
	 * Sets the rating.
	 *
	 * @param rating the new rating
	 */
	public void setRating(Double rating) {
		this.rating = rating;
	}
}
