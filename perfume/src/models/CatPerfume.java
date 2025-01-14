package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CatPerfume {
	private int id;
	private String catPerfume;
	private int parrent_id;
	
	
	/**
	 * @param id
	 * @param catPerfume
	 * @param parrent_id
	 */

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
	 * @return the catPerfume
	 */
	public String getCatPerfume() {
		return catPerfume;
	}

	/**
	 * @param catPerfume the catPerfume to set
	 */
	public void setCatPerfume(String catPerfume) {
		this.catPerfume = catPerfume;
	}

	/**
	 * @return the parrent_id
	 */
	public int getParrent_id() {
		return parrent_id;
	}

	/**
	 * @param parrent_id the parrent_id to set
	 */
	public void setParrent_id(int parrent_id) {
		this.parrent_id = parrent_id;
	}

	public CatPerfume(String catPerfume, int parrent_id) {
		super();
		this.catPerfume = catPerfume;
		this.parrent_id = parrent_id;
	}

	public CatPerfume(String catPerfume) {
		super();
		this.catPerfume = catPerfume;
	}

	public CatPerfume(int id, String catPerfume) {
		super();
		this.id = id;
		this.catPerfume = catPerfume;
	}

	/**
	 * @param id
	 * @param catPerfume
	 * @param parrent_id
	 */
	public CatPerfume(int id, String catPerfume, int parrent_id) {
		super();
		this.id = id;
		this.catPerfume = catPerfume;
		this.parrent_id = parrent_id;
	}

	public CatPerfume(int id) {
		super();
		this.id = id;
	}
	
}
