package ui;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import entity.Customer;
import entity.DiscountedOrder;
import entity.Order;
import entity.Topping;

public class ServiceLayer {
	private Customer signedInCustomer;
	private PizzaShopDAO dao;
	private static ServiceLayer singleton;
	
	public ServiceLayer() {
		dao = new ConcretePizzaShopDAO();
	}
	
	public ServiceLayer(Customer customer) {
		this.signedInCustomer = customer;
		dao = new ConcretePizzaShopDAO();
	}
	
	public static ServiceLayer getSingleton(){
		if(singleton == null){
			singleton = new ServiceLayer();
		}
		return singleton;
	}
	/*
	 * Computes the cost of an order and calls the DAO to persist it.
	 * The order must have a size, a payment method, and a list of toppings.
	 */
	public void makeOrder(Order order) throws NotSignedInException {
		if(!signedIn()) throw new NotSignedInException();
		order.setCustomer(signedInCustomer);
		order.setDeliveryDate(new Timestamp(System.currentTimeMillis()));
		calculateSubtotalOfOrder(order);
		dao.insertOrder(order);
	}
	
	public void makeDiscountedOrder(DiscountedOrder order) throws NotSignedInException {
		if(!signedIn()) throw new NotSignedInException();
		order.setCustomer(signedInCustomer);
		order.setDeliveryDate(new Timestamp(System.currentTimeMillis()));
		calculateSubtotalOfOrder(order);
		dao.insertDiscountedOrder(order);
	}
	

	private void calculateSubtotalOfOrder(Order order) {
		int totalCost = order.getPizzaSize().getPrice();
		for(Topping t : order.getToppings()) {
			totalCost += t.getPrice();
		}
		order.setPrice(totalCost);
		if(order instanceof DiscountedOrder){
			DiscountedOrder discountOrder = (DiscountedOrder) order;
			order.setPrice(order.getPrice() * (1.0 - discountOrder.getDiscount() / 100));
		}
	}
	
	private boolean signedIn(){
		return signedInCustomer != null;
	}
	public List<Order> viewAllOrders() throws NotSignedInException{
		if(!signedIn()) throw new NotSignedInException();
		return dao.getOrdersForCustomer(signedInCustomer);
	}
	
	public void changeOrder(Order order) throws NotSignedInException {
		if(!signedIn()) throw new NotSignedInException();
		calculateSubtotalOfOrder(order);
		dao.updateOrder(order);
	}
	
	public void cancelOrder(Order order) throws NotSignedInException{
		if(!signedIn()) throw new NotSignedInException();
		dao.deleteOrder(order);
	}
	
	public boolean signIn(Customer c) {
		if(!dao.doesCustomerExist(c)) {
			dao.insertCustomer(c);
			signedInCustomer = c;
			return true;
		}
		return false;
	}
	
	public boolean logIn(String username, String password) {
		Customer c = dao.getCustomerFromUsernameAndPassword(username, password);
		if(c != null) {
			signedInCustomer = c;
			return true;
		}
		return false;
	}
}
