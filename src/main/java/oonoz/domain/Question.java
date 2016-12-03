package oonoz.domain;


import java.time.Duration;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The Class Question.
 */
@Entity
@Table(name="QUESTION")
public class Question {
	
	/** The id QUESTION. */
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	/** The title. */
	@Column(name="ID_QCM")
	private long idQCM;
	
	/** The title. */
	@Column(unique = false, nullable = false,name="TITLE")
	private String title;
	
	/** The media type. */
	@Column(unique = false, nullable = true,name="MEDIA_TYPE")
	private String mediaType;
	
	/** The media. */
	@Column(unique = false, nullable = true,name="MEDIA")
	private String media;
	
	/** The answer. */
	@Column(unique = false, nullable = false,name="ANSWER")
	private String answer;
	
	/** The proposition 1. */
	@Column(unique = false, nullable = false,name="PROPOSITION1")
	private String proposition1;
	
	/** The proposition 2. */
	@Column(unique = false, nullable = false,name="PROPOSITION2")
	private String proposition2;
	
	/** The proposition 3. */
	@Column(unique = false, nullable = false,name="PROPOSITION3")
	private String proposition3;
	
	/** The proposition 3. */
	@Column(unique = false, nullable = true,name="TIME")
	private Duration time;

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
	 * Gets the id QCM.
	 *
	 * @return the id QCM
	 */
	public long getIdQCM() {
		return idQCM;
	}

	/**
	 * Sets the id QCM.
	 *
	 * @param idQCM the new id QCM
	 */
	public void setIdQCM(long idQCM) {
		this.idQCM = idQCM;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the media type.
	 *
	 * @return the media type
	 */
	public String getMediaType() {
		return mediaType;
	}

	/**
	 * Sets the media type.
	 *
	 * @param mediaType the new media type
	 */
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	/**
	 * Gets the media.
	 *
	 * @return the media
	 */
	public String getMedia() {
		return media;
	}

	/**
	 * Sets the media.
	 *
	 * @param media the new media
	 */
	public void setMedia(String media) {
		this.media = media;
	}

	/**
	 * Gets the answer.
	 *
	 * @return the answer
	 */
	public String getAnswer() {
		return answer;
	}

	/**
	 * Sets the answer.
	 *
	 * @param answer the new answer
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	/**
	 * Gets the proposition 1.
	 *
	 * @return the proposition 1
	 */
	public String getProposition1() {
		return proposition1;
	}

	/**
	 * Sets the proposition 1.
	 *
	 * @param proposition1 the new proposition 1
	 */
	public void setProposition1(String proposition1) {
		this.proposition1 = proposition1;
	}

	/**
	 * Gets the proposition 2.
	 *
	 * @return the proposition 2
	 */
	public String getProposition2() {
		return proposition2;
	}

	/**
	 * Sets the proposition 2.
	 *
	 * @param proposition2 the new proposition 2
	 */
	public void setProposition2(String proposition2) {
		this.proposition2 = proposition2;
	}

	/**
	 * Gets the proposition 3.
	 *
	 * @return the proposition 3
	 */
	public String getProposition3() {
		return proposition3;
	}

	/**
	 * Sets the proposition 3.
	 *
	 * @param proposition3 the new proposition 3
	 */
	public void setProposition3(String proposition3) {
		this.proposition3 = proposition3;
	}

	/**
	 * Gets the time.
	 *
	 * @return the time
	 */
	public Duration getTime() {
		return time;
	}

	/**
	 * Sets the time.
	 *
	 * @param time the new time
	 */
	public void setTime(Duration time) {
		this.time = time;
	}
}
