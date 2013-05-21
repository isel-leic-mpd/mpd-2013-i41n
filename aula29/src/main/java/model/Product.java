package model;

public class Product {

	int id;
	String productName;
	double unitPrice;
	int unitsInStock;
	Supplier supplier;
	
	public Product(int id, String name, double price, int stock, Supplier supplier) {
		super();
		this.id = id;
		this.productName = name;
		this.unitPrice = price;
		this.unitsInStock = stock;
		this.supplier = supplier;
	}
	
	public Product(String name, double price, int stock, Supplier supplier) {
		this.productName = name;
		this.unitPrice = price;
		this.unitsInStock = stock;
		this.supplier = supplier;

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

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + productName + ", price=" + unitPrice
				+ ", stock=" + unitsInStock + "]";
	}
	
}
