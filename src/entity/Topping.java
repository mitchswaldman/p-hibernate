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
	
	@ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(name="order_topping", 
				joinColumns={@JoinColumn(name="topping_id")}, 
				inverseJoinColumns={@JoinColumn(name="order_id")})
	private List<Order> orders;
	
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
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	@Override
	public String toString(){
		return topping;
	}
}
