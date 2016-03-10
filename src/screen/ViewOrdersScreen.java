package screen;

import java.util.InputMismatchException;
import java.util.List;

import entity.Order;
import ui.BadInputException;
import ui.NotSignedInException;
import ui.ScreenContext;
import ui.ServiceLayer;

public class ViewOrdersScreen extends Screen{

	@Override
	public Object printMessage() {
		try{
			List<Order> orders = ServiceLayer.getSingleton().viewAllOrders();
			if(orders.isEmpty()){
				System.out.println("You haven't order any pizza yet, you horse!");
				return null;
			}
			System.out.println("Here are your orders:");
			orders.forEach(order -> {
				System.out.println(order);
			});
		} catch(NotSignedInException e){
			System.out.println("You must be signed in to view orders.");
		} 
		return null;
	}

	@Override
	public void commitMessage(ScreenContext ctx) {
		try {
			printMessage();
			ctx.setScreen(ctx.getPreviousScreen());
		} catch(Exception e){
			System.out.println("Something went wrong.");
			ctx.setScreen(ctx.getPreviousScreen());
		}
		
	}

}
