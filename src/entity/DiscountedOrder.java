package entity;

import javax.persistence.Entity;

@Entity
public class DiscountedOrder extends Order {
	private double discount;

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	@Override
	public String toString(){
		return "Discount " + super.toString();
	}
}
