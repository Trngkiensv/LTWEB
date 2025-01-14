package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {
	private int id;
	private String username;
	private String fullname;
	private String password;
	private String money;
	CatUser catUser;
	
	/**
	 * @param id
	 * @param username
	 * @param fullname
	 * @param password
	 * @param money
	 * @param catUser
	 */
	public User(int id, String username, String fullname, String password, String money, CatUser catUser) {
		super();
		this.id = id;
		this.username = username;
		this.fullname = fullname;
		this.password = password;
		this.money = money;
		this.catUser = catUser;
	}

	public User(String username, String fullname, String password, String money, CatUser catUser) {
		super();
		this.username = username;
		this.fullname = fullname;
		this.password = password;
		this.money = money;
		this.catUser = catUser;
	}

	public User(String username) {
		super();
		this.username = username;
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
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the fullname
	 */
	public String getFullname() {
		return fullname;
	}

	/**
	 * @param fullname the fullname to set
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the money
	 */
	public String getMoney() {
		return money;
	}

	/**
	 * @param money the money to set
	 */
	public void setMoney(String money) {
		this.money = money;
	}

	/**
	 * @return the catUser
	 */
	public CatUser getCatUser() {
		return catUser;
	}

	/**
	 * @param catUser the catUser to set
	 */
	public void setCatUser(CatUser catUser) {
		this.catUser = catUser;
	}

	public User(int id, String fullname, String password, CatUser catUser) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.password = password;
		this.catUser = catUser;
	}

	public User(int id, String fullname, CatUser catUser) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.catUser = catUser;
	}

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public User(String username, String fullname, String password) {
		super();
		this.username = username;
		this.fullname = fullname;
		this.password = password;
	}

	public User(int id) {
		super();
		this.id = id;
	}

}
