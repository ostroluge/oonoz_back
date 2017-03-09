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

	/** The total comments. */
	private int totalComments;
	
	/** The total ratings. */
	private int totalRatings;
	
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

	/**
	 * Gets the total comments.
	 *
	 * @return the total comments
	 */
	public int getTotalComments() {
		return totalComments;
	}

	/**
	 * Sets the total comments.
	 *
	 * @param totalComments the new total comments
	 */
	public void setTotalComments(int totalComments) {
		this.totalComments = totalComments;
	}

	/**
	 * Gets the total ratings.
	 *
	 * @return the total ratings
	 */
	public int getTotalRatings() {
		return totalRatings;
	}

	/**
	 * Sets the total ratings.
	 *
	 * @param totalRatings the new total ratings
	 */
	public void setTotalRatings(int totalRatings) {
		this.totalRatings = totalRatings;
	}
}
