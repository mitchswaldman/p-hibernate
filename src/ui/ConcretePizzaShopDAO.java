package ui;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.Customer;
import entity.DiscountedOrder;
import entity.Order;

public class ConcretePizzaShopDAO implements PizzaShopDAO{
	private SessionFactory factory;
	public ConcretePizzaShopDAO(){
		factory = HibernateUtil.getSessionFactory();
	}
	@Override
	public void insertOrder(Order order) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		
		session.save(order);
		transaction.commit();
		session.close();
	}

	@Override
	public void insertDiscountedOrder(DiscountedOrder order) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		
		session.save(order);
		transaction.commit();
		session.close();
	}

	@Override
	public void updateOrder(Order updateOrder) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		
		session.update(updateOrder);
		transaction.commit();
		session.close();
	}

	@Override
	public void deleteOrder(Order orderToDelete) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		
		session.delete(orderToDelete);
		transaction.commit();
		session.close();
	}

	@Override
	public List<Order> getOrdersForCustomer(Customer c) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		
		List<Order> orders = null;
		String queryString = "from Order where customer = :c";
		Query query = session.createQuery(queryString);
		query.setParameter("c", c);
		orders = query.list();
		
		transaction.commit();
		session.close();
		return orders;
	}

	@Override
	public boolean doesCustomerExist(Customer c) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		
		boolean exists = false;
		String queryString = "from Customer where userName = :userName";
		Query query = session.createQuery(queryString);
		query.setString("userName" , c.getUserName());
		if(query.uniqueResult() != null){
			exists = true;
		}
		transaction.commit();
		session.close();
		return exists;
	}

	@Override
	public void insertCustomer(Customer customer) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		
		session.save(customer);
		transaction.commit();
		session.close();
	}

	@Override
	public Customer getCustomerFromUsernameAndPassword(String username, String password) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		
		String queryString = "from Customer where userName = :userName and password = :password";
		Query query = session.createQuery(queryString);
		query.setString("userName" , username);
		query.setString("password" , password);
		Customer c = (Customer) query.uniqueResult();
		
		transaction.commit();
		session.close();
		return c;
	}

}
