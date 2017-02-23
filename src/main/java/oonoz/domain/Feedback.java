package oonoz.domain;

import java.util.Map;

/**
 * The Class Feedback.
 */
public class Feedback {

	/** The comments. */
	private Map<String, String> comments;
	
	/** The average rating. */
	private double averageRating;

	/**
	 * Gets the comments.
	 *
	 * @return the comments
	 */
	public Map<String, String> getComments() {
		return comments;
	}

	/**
	 * Sets the comments.
	 *
	 * @param comments the new comments
	 */
	public void setComments(Map<String, String> comments) {
		this.comments = comments;
	}

	/**
	 * Gets the average rating.
	 *
	 * @return the average rating
	 */
	public double getAverageRating() {
		return averageRating;
	}

	/**
	 * Sets the average rating.
	 *
	 * @param averageRating the new average rating
	 */
	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}
}
