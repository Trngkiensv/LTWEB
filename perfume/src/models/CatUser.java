package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CatUser {
	private int id;
	private String catUser;
	private Boolean addquyen;
	private Boolean editquyen;
	private Boolean delquyen;
	
	public CatUser(String catUser) {
		super();
		this.catUser = catUser;
	}

	public CatUser(int id) {
		super();
		this.id = id;
	}

	public CatUser(int id, String catUser) {
		super();
		this.id = id;
		this.catUser = catUser;
	}

	public CatUser(int id, Boolean addquyen, Boolean editquyen, Boolean delquyen) {
		super();
		this.id = id;
		this.addquyen = addquyen;
		this.editquyen = editquyen;
		this.delquyen = delquyen;
	}

	/**
	 * @param id
	 * @param catUser
	 * @param addquyen
	 * @param editquyen
	 * @param delquyen
	 */
	public CatUser(int id, String catUser, Boolean addquyen, Boolean editquyen, Boolean delquyen) {
		super();
		this.id = id;
		this.catUser = catUser;
		this.addquyen = addquyen;
		this.editquyen = editquyen;
		this.delquyen = delquyen;
	}

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
	 * @return the catUser
	 */
	public String getCatUser() {
		return catUser;
	}

	/**
	 * @param catUser the catUser to set
	 */
	public void setCatUser(String catUser) {
		this.catUser = catUser;
	}

	/**
	 * @return the addquyen
	 */
	public Boolean getAddquyen() {
		return addquyen;
	}

	/**
	 * @param addquyen the addquyen to set
	 */
	public void setAddquyen(Boolean addquyen) {
		this.addquyen = addquyen;
	}

	/**
	 * @return the editquyen
	 */
	public Boolean getEditquyen() {
		return editquyen;
	}

	/**
	 * @param editquyen the editquyen to set
	 */
	public void setEditquyen(Boolean editquyen) {
		this.editquyen = editquyen;
	}

	/**
	 * @return the delquyen
	 */
	public Boolean getDelquyen() {
		return delquyen;
	}

	/**
	 * @param delquyen the delquyen to set
	 */
	public void setDelquyen(Boolean delquyen) {
		this.delquyen = delquyen;
	}
	
}
