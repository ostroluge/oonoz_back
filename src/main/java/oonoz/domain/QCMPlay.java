package oonoz.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The Class QCMPlay.
 */
@Entity
@Table(name="PLAY_QCM")
public class QCMPlay {

	/** The id. */
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	/** The id qcm. */
	@Column(unique = true, nullable = false, name="ID_QCM")
	private long idQcm;

	/** The id player. */
	@Column(unique = true, nullable = false, name="ID_PLAYER")
	private long idPlayer;
	
	/** The player. */
	@OneToOne
	@JoinColumn(name="ID_PLAYER",insertable = false,updatable = false)
	private Player player;
	
	/** The question 1. */
	@Column(name="QUESTION1")
	private boolean question1;
	
	/** The question 2. */
	@Column(name="QUESTION2")
	private boolean question2;
	
	/** The question 3. */
	@Column(name="QUESTION3")
	private boolean question3;
	
	/** The question 4. */
	@Column(name="QUESTION4")
	private boolean question4;
	
	/** The question 5. */
	@Column(name="QUESTION5")
	private boolean question5;
	
	/** The question 6. */
	@Column(name="QUESTION6")
	private boolean question6;
	
	/** The question 7. */
	@Column(name="QUESTION7")
	private boolean question7;
	
	/** The question 8. */
	@Column(name="QUESTION8")
	private boolean question8;
	
	/** The question 9. */
	@Column(name="QUESTION9")
	private boolean question9;
	
	/** The question 10. */
	@Column(name="QUESTION10")
	private boolean question10;
	
	/** The question 11. */
	@Column(name="QUESTION11")
	private boolean question11;
	
	/** The question 12. */
	@Column(name="QUESTION12")
	private boolean question12;
	
	/** The question 13. */
	@Column(name="QUESTION13")
	private boolean question13;
	
	/** The question 14. */
	@Column(name="QUESTION14")
	private boolean question14;
	
	/** The question 15. */
	@Column(name="QUESTION15")
	private boolean question15;
	
	/** The question 16. */
	@Column(name="QUESTION16")
	private boolean question16;
	
	/** The question 17. */
	@Column(name="QUESTION17")
	private boolean question17;
	
	/** The question 18. */
	@Column(name="QUESTION18")
	private boolean question18;
	
	/** The question 19. */
	@Column(name="QUESTION19")
	private boolean question19;
	
	/** The question 20. */
	@Column(name="QUESTION20")
	private boolean question20;

	/** The score. */
	@Column(name="SCORE")
	private Integer score;
	
	/** The comment. */
	@Column(name="COMMENT")
	private String comment;
	
	/** The finished. */
	@Column(nullable = false, name="FINISHED")
	private boolean finished;
	
	
	/** The finished. */
	@Column(nullable = false, name="NOTE")
	private Integer note;
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
	 * Gets the id qcm.
	 *
	 * @return the id qcm
	 */
	public long getIdQcm() {
		return idQcm;
	}

	/**
	 * Sets the id qcm.
	 *
	 * @param idQcm the new id qcm
	 */
	public void setIdQcm(long idQcm) {
		this.idQcm = idQcm;
	}

	/**
	 * Gets the id player.
	 *
	 * @return the id player
	 */
	public long getIdPlayer() {
		return idPlayer;
	}

	/**
	 * Sets the id player.
	 *
	 * @param idPlayer the new id player
	 */
	public void setIdPlayer(long idPlayer) {
		this.idPlayer = idPlayer;
	}

	/**
	 * Checks if is question 1.
	 *
	 * @return true, if is question 1
	 */
	public boolean isQuestion1() {
		return question1;
	}

	/**
	 * Sets the question 1.
	 *
	 * @param question1 the new question 1
	 */
	public void setQuestion1(boolean question1) {
		this.question1 = question1;
	}

	/**
	 * Checks if is question 2.
	 *
	 * @return true, if is question 2
	 */
	public boolean isQuestion2() {
		return question2;
	}

	/**
	 * Sets the question 2.
	 *
	 * @param question2 the new question 2
	 */
	public void setQuestion2(boolean question2) {
		this.question2 = question2;
	}

	/**
	 * Checks if is question 3.
	 *
	 * @return true, if is question 3
	 */
	public boolean isQuestion3() {
		return question3;
	}

	/**
	 * Sets the question 3.
	 *
	 * @param question3 the new question 3
	 */
	public void setQuestion3(boolean question3) {
		this.question3 = question3;
	}

	/**
	 * Checks if is question 4.
	 *
	 * @return true, if is question 4
	 */
	public boolean isQuestion4() {
		return question4;
	}

	/**
	 * Sets the question 4.
	 *
	 * @param question4 the new question 4
	 */
	public void setQuestion4(boolean question4) {
		this.question4 = question4;
	}

	/**
	 * Checks if is question 5.
	 *
	 * @return true, if is question 5
	 */
	public boolean isQuestion5() {
		return question5;
	}

	/**
	 * Sets the question 5.
	 *
	 * @param question5 the new question 5
	 */
	public void setQuestion5(boolean question5) {
		this.question5 = question5;
	}

	/**
	 * Checks if is question 6.
	 *
	 * @return true, if is question 6
	 */
	public boolean isQuestion6() {
		return question6;
	}

	/**
	 * Sets the question 6.
	 *
	 * @param question6 the new question 6
	 */
	public void setQuestion6(boolean question6) {
		this.question6 = question6;
	}

	/**
	 * Checks if is question 7.
	 *
	 * @return true, if is question 7
	 */
	public boolean isQuestion7() {
		return question7;
	}

	/**
	 * Sets the question 7.
	 *
	 * @param question7 the new question 7
	 */
	public void setQuestion7(boolean question7) {
		this.question7 = question7;
	}

	/**
	 * Checks if is question 8.
	 *
	 * @return true, if is question 8
	 */
	public boolean isQuestion8() {
		return question8;
	}

	/**
	 * Sets the question 8.
	 *
	 * @param question8 the new question 8
	 */
	public void setQuestion8(boolean question8) {
		this.question8 = question8;
	}

	/**
	 * Checks if is question 9.
	 *
	 * @return true, if is question 9
	 */
	public boolean isQuestion9() {
		return question9;
	}

	/**
	 * Sets the question 9.
	 *
	 * @param question9 the new question 9
	 */
	public void setQuestion9(boolean question9) {
		this.question9 = question9;
	}

	/**
	 * Checks if is question 10.
	 *
	 * @return true, if is question 10
	 */
	public boolean isQuestion10() {
		return question10;
	}

	/**
	 * Sets the question 10.
	 *
	 * @param question10 the new question 10
	 */
	public void setQuestion10(boolean question10) {
		this.question10 = question10;
	}

	/**
	 * Checks if is question 11.
	 *
	 * @return true, if is question 11
	 */
	public boolean isQuestion11() {
		return question11;
	}

	/**
	 * Sets the question 11.
	 *
	 * @param question11 the new question 11
	 */
	public void setQuestion11(boolean question11) {
		this.question11 = question11;
	}

	/**
	 * Checks if is question 12.
	 *
	 * @return true, if is question 12
	 */
	public boolean isQuestion12() {
		return question12;
	}

	/**
	 * Sets the question 12.
	 *
	 * @param question12 the new question 12
	 */
	public void setQuestion12(boolean question12) {
		this.question12 = question12;
	}

	/**
	 * Checks if is question 13.
	 *
	 * @return true, if is question 13
	 */
	public boolean isQuestion13() {
		return question13;
	}

	/**
	 * Sets the question 13.
	 *
	 * @param question13 the new question 13
	 */
	public void setQuestion13(boolean question13) {
		this.question13 = question13;
	}

	/**
	 * Checks if is question 14.
	 *
	 * @return true, if is question 14
	 */
	public boolean isQuestion14() {
		return question14;
	}

	/**
	 * Sets the question 14.
	 *
	 * @param question14 the new question 14
	 */
	public void setQuestion14(boolean question14) {
		this.question14 = question14;
	}

	/**
	 * Checks if is question 15.
	 *
	 * @return true, if is question 15
	 */
	public boolean isQuestion15() {
		return question15;
	}

	/**
	 * Sets the question 15.
	 *
	 * @param question15 the new question 15
	 */
	public void setQuestion15(boolean question15) {
		this.question15 = question15;
	}

	/**
	 * Checks if is question 16.
	 *
	 * @return true, if is question 16
	 */
	public boolean isQuestion16() {
		return question16;
	}

	/**
	 * Sets the question 16.
	 *
	 * @param question16 the new question 16
	 */
	public void setQuestion16(boolean question16) {
		this.question16 = question16;
	}

	/**
	 * Checks if is question 17.
	 *
	 * @return true, if is question 17
	 */
	public boolean isQuestion17() {
		return question17;
	}

	/**
	 * Sets the question 17.
	 *
	 * @param question17 the new question 17
	 */
	public void setQuestion17(boolean question17) {
		this.question17 = question17;
	}

	/**
	 * Checks if is question 18.
	 *
	 * @return true, if is question 18
	 */
	public boolean isQuestion18() {
		return question18;
	}

	/**
	 * Sets the question 18.
	 *
	 * @param question18 the new question 18
	 */
	public void setQuestion18(boolean question18) {
		this.question18 = question18;
	}

	/**
	 * Checks if is question 19.
	 *
	 * @return true, if is question 19
	 */
	public boolean isQuestion19() {
		return question19;
	}

	/**
	 * Sets the question 19.
	 *
	 * @param question19 the new question 19
	 */
	public void setQuestion19(boolean question19) {
		this.question19 = question19;
	}

	/**
	 * Checks if is question 20.
	 *
	 * @return true, if is question 20
	 */
	public boolean isQuestion20() {
		return question20;
	}

	/**
	 * Sets the question 20.
	 *
	 * @param question20 the new question 20
	 */
	public void setQuestion20(boolean question20) {
		this.question20 = question20;
	}

	/**
	 * Gets the score.
	 *
	 * @return the score
	 */
	public Integer getScore() {
		return score;
	}

	/**
	 * Sets the score.
	 *
	 * @param score the new score
	 */
	public void setScore(Integer score) {
		this.score = score;
	}

	/**
	 * Gets the comment.
	 *
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * Sets the comment.
	 *
	 * @param comment the new comment
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * Checks if is finished.
	 *
	 * @return true, if is finished
	 */
	public boolean isFinished() {
		return finished;
	}

	/**
	 * Sets the finished.
	 *
	 * @param finished the new finished
	 */
	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	/**
	 * Gets the player.
	 *
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Sets the player.
	 *
	 * @param player the new player
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	public Integer getNote() {
		return note;
	}

	public void setNote(Integer note) {
		this.note = note;
	}
	
	
}
