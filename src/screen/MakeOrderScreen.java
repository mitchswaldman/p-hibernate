package screen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import entity.Order;
import entity.PaymentMethod;
import entity.PizzaSize;
import entity.Topping;
import ui.BadInputException;
import ui.NotSignedInException;
import ui.ScreenContext;
import ui.ServiceLayer;

public class MakeOrderScreen extends Screen {
	private List<Topping> toppings;
	private static final int MAX_TOPPINGS = 4;
	public MakeOrderScreen(){
		toppings = new ArrayList<>();
		toppings.add(new Topping("Pepperoni", 1.00));
		toppings.add(new Topping("Mushrooms", 0.75));
		toppings.add(new Topping("Onions", 1.00));
		toppings.add(new Topping("Sausage", 1.00));
		toppings.add(new Topping("Bacon", 1.00));
		toppings.add(new Topping("Extra cheese", 1.00));
		toppings.add(new Topping("Black olives", 1.00));
		toppings.add(new Topping("Green peppers", 1.00));
		toppings.add(new Topping("Pineapple", 1.00));
		toppings.add(new Topping("Spinach", 1.00));
	}

	@Override
	public void commitMessage(ScreenContext ctx) {
		try {
			Order order = (Order) printMessage();
			ServiceLayer.getSingleton().makeOrder(order);
			ctx.setScreen(ctx.getPreviousScreen());
		} catch(NotSignedInException e){
			System.out.println("You must sign in before making an order!");
			ctx.setScreen(new HomeScreen());
		} catch(Exception e) {
			System.out.println("Something went horribly wrong.");
			System.out.println(e.getLocalizedMessage());
			ctx.setScreen(ctx.getPreviousScreen());
		}
	}

	@Override
	public Object printMessage() {
		try {
			PizzaSize size = (PizzaSize) new SizeSelectionScreen().printMessage();
			List<Topping> selectedToppings = (List<Topping>) new ToppingSelectionScreen().printMessage();
			PaymentMethod paymentMethod = (PaymentMethod) new PaymentMethodScreen().printMessage();
			Order order = new Order();
			order.setPaymentMethod(paymentMethod);
			order.setPizzaSize(size);
			order.setToppings(selectedToppings);
			return order;
		} catch(InputMismatchException e){
			System.out.println("Bad input. Try again.");
			scanner.nextLine();
			return printMessage();
		} catch(Exception e){
			return null;
		}
	}

}
