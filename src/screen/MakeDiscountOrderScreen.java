package screen;

import entity.DiscountedOrder;
import entity.Order;
import ui.NotSignedInException;
import ui.ScreenContext;
import ui.ServiceLayer;

public class MakeDiscountOrderScreen extends Screen {
	private static final double DISCOUNT = 10;
	@Override
	public Object printMessage() {
		System.out.printf("DISCOUNT SET AT %.2f percent!\n", DISCOUNT);
		Order order = (Order) new MakeOrderScreen().printMessage();
		DiscountedOrder discountOrder = new DiscountedOrder();
		discountOrder.setDiscount(DISCOUNT);
		discountOrder.setToppings(order.getToppings());
		discountOrder.setPizzaSize(order.getPizzaSize());
		discountOrder.setPaymentMethod(order.getPaymentMethod());
		return discountOrder;
	}

	@Override
	public void commitMessage(ScreenContext ctx) {
		try {
			DiscountedOrder order = (DiscountedOrder) printMessage();
			ServiceLayer.getSingleton().makeDiscountedOrder(order);
			ctx.setScreen(ctx.getPreviousScreen());
		} catch(NotSignedInException e){
			System.out.println("You have to be signed in to order a pizza!");
			System.out.println(e.getLocalizedMessage());
			ctx.setScreen(new HomeScreen());
		} catch(Exception e){
			System.out.println("Something went horribly wrong.");
			System.out.println(e.getLocalizedMessage());
			ctx.setScreen(ctx.getPreviousScreen());
		}
		
	}

}
