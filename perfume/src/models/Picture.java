package models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Picture {
	private int id;
	private String picture;
	Perfume product;
	
	public Picture(int id) {
		super();
		this.id = id;
	}

	/**
	 * @param id
	 * @param picture
	 * @param product
	 */
	public Picture(int id, String picture, Perfume product) {
		super();
		this.id = id;
		this.picture = picture;
		this.product = product;
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
	 * @return the picture
	 */
	public String getPicture() {
		return picture;
	}

	/**
	 * @param picture the picture to set
	 */
	public void setPicture(String picture) {
		this.picture = picture;
	}

	/**
	 * @return the product
	 */
	public Perfume getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(Perfume product) {
		this.product = product;
	}

	public Picture(String picture, Perfume product) {
		super();
		this.picture = picture;
		this.product = product;
	}

	public Picture(Perfume product) {
		super();
		this.product = product;
	}

	public Picture(int id, String picture) {
		super();
		this.id = id;
		this.picture = picture;
	}

	public Picture(String picture) {
		super();
		this.picture = picture;
	}

}
