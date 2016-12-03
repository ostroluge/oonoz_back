package oonoz.domain;


import java.time.Duration;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="QUESTION")
public class Question {
	
	/** The id QUESTION. */
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	/** The title. */
	@Column(unique = false, nullable = false,name="ID_QCM")
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdQCM() {
		return idQCM;
	}

	public void setIdQCM(long idQCM) {
		this.idQCM = idQCM;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	

	public String getProposition1() {
		return proposition1;
	}

	public void setProposition1(String proposition1) {
		this.proposition1 = proposition1;
	}

	public String getProposition2() {
		return proposition2;
	}

	public void setProposition2(String proposition2) {
		this.proposition2 = proposition2;
	}

	public String getProposition3() {
		return proposition3;
	}

	public void setProposition3(String proposition3) {
		this.proposition3 = proposition3;
	}

	public Duration getTime() {
		return time;
	}

	public void setTime(Duration time) {
		this.time = time;
	}
	
	

}
