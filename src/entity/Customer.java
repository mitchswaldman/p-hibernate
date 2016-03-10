package entity;
import java.util.List;

import javax.persistence.*;

@Entity
public class Customer {
	private int customerId;
	private String userName;
	private String password;
	
	private Address address;
	
	
	@Id
	@Column(name="customer_id")
	@GeneratedValue
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int id) {
		customerId = id;
	}
	
	@Column(unique=true)
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
}
