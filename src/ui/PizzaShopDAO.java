package ui;
import java.util.List;

import entity.Customer;
import entity.DiscountedOrder;
import entity.Order;

public interface PizzaShopDAO {
	void insertOrder(Order order);
	void insertDiscountedOrder(DiscountedOrder order);
	void updateOrder(Order updateOrder);
	void deleteOrder(Order orderToDelete);
	List<Order> getOrdersForCustomer(Customer c); 	
	boolean doesCustomerExist(Customer c);
	void insertCustomer(Customer customer);
	Customer getCustomerFromUsernameAndPassword(String username, String password);
}
