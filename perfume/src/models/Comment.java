package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Comment {
	private int id;
	private String name_comment;
	private String create_comment;
	private String message;
	private int id_per;
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name_comment
	 */
	public String getName_comment() {
		return name_comment;
	}

	/**
	 * @param name_comment the name_comment to set
	 */
	public void setName_comment(String name_comment) {
		this.name_comment = name_comment;
	}

	/**
	 * @return the create_comment
	 */
	public String getCreate_comment() {
		return create_comment;
	}

	/**
	 * @param create_comment the create_comment to set
	 */
	public void setCreate_comment(String create_comment) {
		this.create_comment = create_comment;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the id_per
	 */
	public int getId_per() {
		return id_per;
	}

	/**
	 * @param id_per the id_per to set
	 */
	public void setId_per(int id_per) {
		this.id_per = id_per;
	}

	public Comment(int id, String name_comment, String message) {
		super();
		this.id = id;
		this.name_comment = name_comment;
		this.message = message;
	}

	public Comment(String name_comment, String create_comment, String message) {
		super();
		this.name_comment = name_comment;
		this.create_comment = create_comment;
		this.message = message;
	}

	public Comment(String name_comment, String message, int id_per) {
		super();
		this.name_comment = name_comment;
		this.message = message;
		this.id_per = id_per;
	}
}
