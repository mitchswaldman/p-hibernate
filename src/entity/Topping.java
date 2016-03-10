package entity;
import java.util.List;

import javax.persistence.*;

@Entity
public class Topping {
	@Id
	@GeneratedValue
	private int tid;
	private String topping;
	private double price;
	
	public Topping(){
		
	}
	
	public Topping(String topping, double price){
		this.topping = topping;
		this.price = price;
	}
	
	public String getTopping() {
		return topping;
	}
	public void setTopping(String topping) {
		this.topping = topping;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	
	@Override
	public String toString(){
		return topping;
	}
}
