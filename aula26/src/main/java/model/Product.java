package model;

public class Product {

	final int id;
	final String productName;
	final double unitPrice;
	final int unitsInStock;

	public Product(int id, String name, double price, int stock) {
		super();
		this.id = id;
		this.productName = name;
		this.unitPrice = price;
		this.unitsInStock = stock;
	}

	
	
	public int getId() {
		return id;
	}



	public String getProductName() {
		return productName;
	}



	public double getUnitPrice() {
		return unitPrice;
	}



	public int getUnitsInStock() {
		return unitsInStock;
	}



	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + productName + ", price=" + unitPrice
				+ ", stock=" + unitsInStock + "]";
	}	
}
