package model;

public class Product {

	int id;
	String productName;
	public void setId(int id) {
		this.id = id;
	}



	public void setProductName(String productName) {
		this.productName = productName;
	}



	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}



	public void setUnitsInStock(int unitsInStock) {
		this.unitsInStock = unitsInStock;
	}



	double unitPrice;
	int unitsInStock;

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
