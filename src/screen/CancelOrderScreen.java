package screen;

import java.util.List;

import entity.Order;
import ui.NotSignedInException;
import ui.ScreenContext;
import ui.ServiceLayer;

public class CancelOrderScreen extends Screen {
	@Override
	public Object printMessage() {
		try {
			List<Order> orders = ServiceLayer.getSingleton().viewAllOrders();
			if(orders.isEmpty()){
				System.out.println("You have no orders. Order some 'za first, ya donkey!");
				return null;
			}
			
			System.out.println("Which order would you like to cancel?");
			for(int i = 0; i < orders.size(); i++){
				System.out.println((i + 1) + ") " + orders.get(i)+"\n");
			}
			int orderOrdinal = scanner.nextInt();
			scanner.nextLine();
			verifyInput(orderOrdinal > 0 && orderOrdinal <= orders.size());
			Order orderToCancel = orders.get(orderOrdinal - 1);
			ServiceLayer.getSingleton().cancelOrder(orderToCancel);
		} catch(NotSignedInException e){
			System.out.println("You must be signed in to cancel an order!");
		} catch(Exception e){
			System.out.println("Something bad happened.");
		}
		return null;
	}

	@Override
	public void commitMessage(ScreenContext ctx) {
		try {
			printMessage();
			ctx.setScreen(ctx.getPreviousScreen());
		} catch(Exception e){
			System.out.println("Something bad happened");
			ctx.setScreen(ctx.getPreviousScreen());
		}
	}

}
