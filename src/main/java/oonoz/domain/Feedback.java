package oonoz.domain;

import java.util.List;

/**
 * The Class Feedback.
 */
public class Feedback {

	/** The comments. */
	private List<Comment> comments;
	
	/** The average rating. */
	private double averageRating;

	/**
	 * Gets the comments.
	 *
	 * @return the comments
	 */
	public List<Comment> getComments() {
		return comments;
	}

	/**
	 * Sets the comments.
	 *
	 * @param comments the new comments
	 */
	public void setComments(List<Comment> comments) {
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
