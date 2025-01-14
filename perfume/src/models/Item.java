package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
	private int id;
	private Perfume product;
	private int quantity;
	private long price;
	private Order order;

	public Item(int id) {
		super();
		this.id = id;
	}

	public Item() {
		// TODO Auto-generated constructor stub
	}

	public Item(Perfume product, int quantity, long price, Order order) {
		super();
		this.product = product;
		this.quantity = quantity;
		this.price = price;
		this.order = order;
	}

	/**
	 * @param id
	 * @param product
	 * @param quantity
	 * @param price
	 * @param order
	 */
	public Item(int id, Perfume product, int quantity, long price, Order order) {
		super();
		this.id = id;
		this.product = product;
		this.quantity = quantity;
		this.price = price;
		this.order = order;
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

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the price
	 */
	public long getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(long price) {
		this.price = price;
	}

	/**
	 * @return the order
	 */
	public Order getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(Order order) {
		this.order = order;
	}

}
