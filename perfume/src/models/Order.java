package models;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Order implements Serializable {
	private int id;
	private User customer;
	private List<Item> item;
	private Boolean status;
	private String phone;
	private String email;
	private String adress;
	private String note;
	public Order() {
		// TODO Auto-generated constructor stub
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
	 * @return the customer
	 */
	public User getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(User customer) {
		this.customer = customer;
	}

	/**
	 * @return the item
	 */
	public List<Item> getItem() {
		return item;
	}

	/**
	 * @param item the item to set
	 */
	public void setItem(List<Item> item) {
		this.item = item;
	}

	/**
	 * @return the status
	 */
	public Boolean getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the adress
	 */
	public String getAdress() {
		return adress;
	}

	/**
	 * @param adress the adress to set
	 */
	public void setAdress(String adress) {
		this.adress = adress;
	}

	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}

	public Order(User customer, String phone, String email, String adress, String note) {
		super();
		this.customer = customer;
		this.phone = phone;
		this.email = email;
		this.adress = adress;
		this.note = note;
	}

	public Order(int id) {
		super();
		this.id = id;
	}

	public Order(int id, User customer, Boolean status, String phone, String email, String adress, String note) {
		super();
		this.id = id;
		this.customer = customer;
		this.status = status;
		this.phone = phone;
		this.email = email;
		this.adress = adress;
		this.note = note;
	}

	public Order(int id, Boolean status) {
		super();
		this.id = id;
		this.status = status;
	}
	
}
