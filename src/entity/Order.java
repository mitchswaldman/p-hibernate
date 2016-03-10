package entity;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="PIZZA_ORDER")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Order {
	@Id
	@GeneratedValue
	private int orderId;
	private double price;
	private Timestamp deliveryDate;
	@Enumerated(EnumType.STRING)
	private PizzaSize pizzaSize;
	@Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(name="order_topping", 
				joinColumns={@JoinColumn(name="order_id")}, 
				inverseJoinColumns={@JoinColumn(name="topping_id")})
	private List<Topping> toppings;
	
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Timestamp getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Timestamp deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public PizzaSize getPizzaSize() {
		return pizzaSize;
	}
	public void setPizzaSize(PizzaSize pizzaSize) {
		this.pizzaSize = pizzaSize;
	}
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public List<Topping> getToppings() {
		return toppings;
	}
	public void setToppings(List<Topping> toppings) {
		this.toppings = toppings;
	}
	
	@Override
	public String toString(){
		return "Order:"+
				"\n\tTotal Price: $" + getPrice()+
				"\n\tPay by: " + getPaymentMethod() +
				"\n\tSize: "+getPizzaSize()+
				"\n\tToppings: "+getToppings()+
				"\n\tDelivery Date: " +getDeliveryDate()+
				"\n\tCustomer: "+getCustomer().getUserName();	
	}
	
}
