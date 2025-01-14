package models;

import java.sql.Timestamp;
import java.util.List;

import javax.management.loading.PrivateClassLoader;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Perfume {
	private int id;
	private String perfumes;
	private String description;
	private String detail;
	private String brand;
	private String made;
	private String capacity;
	private String codePerfume;
	private int amount;
	private long money;
	private int evaluate;
	private Timestamp create_date;
	private int views;
	CatPerfume catPer;
	
	/**
	 * @param id
	 * @param perfumes
	 * @param description
	 * @param detail
	 * @param brand
	 * @param made
	 * @param capacity
	 * @param codePerfume
	 * @param amount
	 * @param money
	 * @param evaluate
	 * @param create_date
	 * @param views
	 * @param catPer
	 */
	public Perfume(int id, String perfumes, String description, String detail, String brand, String made,
			String capacity, String codePerfume, int amount, long money, int evaluate, Timestamp create_date, int views,
			CatPerfume catPer) {
		super();
		this.id = id;
		this.perfumes = perfumes;
		this.description = description;
		this.detail = detail;
		this.brand = brand;
		this.made = made;
		this.capacity = capacity;
		this.codePerfume = codePerfume;
		this.amount = amount;
		this.money = money;
		this.evaluate = evaluate;
		this.create_date = create_date;
		this.views = views;
		this.catPer = catPer;
	}
	public Perfume(int id, String perfumes, String brand, int amount, long money, CatPerfume catPer) {
		super();
		this.id = id;
		this.perfumes = perfumes;
		this.brand = brand;
		this.amount = amount;
		this.money = money;
		this.catPer = catPer;
	}
	public Perfume(int id) {
		super();
		this.id = id;
	}
	
	/*
	 * public Perfume(String perfumes, String description, String detail, String
	 * brand, String made, String capacity, String codePerfume, int amount, long
	 * money, List<String> arpic, CatPerfume catPer) { super(); this.perfumes =
	 * perfumes; this.description = description; this.detail = detail; this.brand =
	 * brand; this.made = made; this.capacity = capacity; this.codePerfume =
	 * codePerfume; this.amount = amount; this.money = money; this.arpic = arpic;
	 * this.catPer = catPer; }
	 */
	/*
	 * public Perfume(int id, String perfumes, String description, String detail,
	 * String brand, String made, String capacity, String codePerfume, int amount,
	 * long money, int evaluate, Timestamp create_date, int views, CatPerfume
	 * catPer) { super(); this.id = id; this.perfumes = perfumes; this.description =
	 * description; this.detail = detail; this.brand = brand; this.made = made;
	 * this.capacity = capacity; this.codePerfume = codePerfume; this.amount =
	 * amount; this.money = money; this.evaluate = evaluate; this.create_date =
	 * create_date; this.views = views; this.catPer = catPer; }
	 */
	public Perfume(int id, String perfumes, String description, String detail, String brand, String made,
			String capacity, String codePerfume, int amount, long money, CatPerfume catPer) {
		super();
		this.id = id;
		this.perfumes = perfumes;
		this.description = description;
		this.detail = detail;
		this.brand = brand;
		this.made = made;
		this.capacity = capacity;
		this.codePerfume = codePerfume;
		this.amount = amount;
		this.money = money;
		this.catPer = catPer;
	}
	public Perfume(String perfumes, String description, String detail, String brand, String made, String capacity,
			String codePerfume, int amount, long money, CatPerfume catPer) {
		super();
		this.perfumes = perfumes;
		this.description = description;
		this.detail = detail;
		this.brand = brand;
		this.made = made;
		this.capacity = capacity;
		this.codePerfume = codePerfume;
		this.amount = amount;
		this.money = money;
		this.catPer = catPer;
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
	 * @return the perfumes
	 */
	public String getPerfumes() {
		return perfumes;
	}
	/**
	 * @param perfumes the perfumes to set
	 */
	public void setPerfumes(String perfumes) {
		this.perfumes = perfumes;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the detail
	 */
	public String getDetail() {
		return detail;
	}
	/**
	 * @param detail the detail to set
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}
	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}
	/**
	 * @param brand the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}
	/**
	 * @return the made
	 */
	public String getMade() {
		return made;
	}
	/**
	 * @param made the made to set
	 */
	public void setMade(String made) {
		this.made = made;
	}
	/**
	 * @return the capacity
	 */
	public String getCapacity() {
		return capacity;
	}
	/**
	 * @param capacity the capacity to set
	 */
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	/**
	 * @return the codePerfume
	 */
	public String getCodePerfume() {
		return codePerfume;
	}
	/**
	 * @param codePerfume the codePerfume to set
	 */
	public void setCodePerfume(String codePerfume) {
		this.codePerfume = codePerfume;
	}
	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}
	/**
	 * @return the money
	 */
	public long getMoney() {
		return money;
	}
	/**
	 * @param money the money to set
	 */
	public void setMoney(long money) {
		this.money = money;
	}
	/**
	 * @return the evaluate
	 */
	public int getEvaluate() {
		return evaluate;
	}
	/**
	 * @param evaluate the evaluate to set
	 */
	public void setEvaluate(int evaluate) {
		this.evaluate = evaluate;
	}
	/**
	 * @return the create_date
	 */
	public Timestamp getCreate_date() {
		return create_date;
	}
	/**
	 * @param create_date the create_date to set
	 */
	public void setCreate_date(Timestamp create_date) {
		this.create_date = create_date;
	}
	/**
	 * @return the views
	 */
	public int getViews() {
		return views;
	}
	/**
	 * @param views the views to set
	 */
	public void setViews(int views) {
		this.views = views;
	}
	/**
	 * @return the catPer
	 */
	public CatPerfume getCatPer() {
		return catPer;
	}
	/**
	 * @param catPer the catPer to set
	 */
	public void setCatPer(CatPerfume catPer) {
		this.catPer = catPer;
	}
	
}
