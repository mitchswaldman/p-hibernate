package entity;
import javax.persistence.Embeddable;

@Embeddable
public class Address {
	private String streetName;
	private String city;
	private String state;
	private String zipcode;
	
	public Address(){
		
	}
	
	public Address(String streetName, String city, String state, String zipcode) {
		this.streetName = streetName;
		this.state = state;
		this.city = city;
		setZipcode(zipcode);
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		if(!zipcode.matches("[0-9]{5}")) {
			this.zipcode = "12345";
			return;
		}
		this.zipcode = zipcode;
	}
}
