package screen;

import java.util.InputMismatchException;
import java.util.List;

import entity.Order;
import entity.PaymentMethod;
import entity.PizzaSize;
import entity.Topping;
import ui.BadInputException;
import ui.NotSignedInException;
import ui.ScreenContext;
import ui.ServiceLayer;

public class ChangeOrderScreen extends Screen{

	@Override
	public Object printMessage() {
		try {
			List<Order> orders = ServiceLayer.getSingleton().viewAllOrders();
			if(orders.isEmpty()){
				System.out.println("You have no orders. Order some 'za first, ya donkey!");
				return null;
			}
			
			System.out.println("Which order would you like to change?");
			for(int i = 0; i < orders.size(); i++){
				System.out.println((i + 1) + ") " + orders.get(i)+"\n");
			}
			int orderOrdinal = scanner.nextInt();
			scanner.nextLine();
			verifyInput(orderOrdinal > 0 && orderOrdinal <= orders.size());
			Order orderToChange = orders.get(orderOrdinal - 1);
			
			System.out.println("What part of your order would you like to change?");
			System.out.println("1) Size \n2) Toppings \n3)Payment method \n4)Nothin");
			int choice = scanner.nextInt();
			scanner.nextLine();
			verifyInput(choice > 0 && choice  < 3);
			switch(choice){
			case 1: {
				PizzaSize size = (PizzaSize) new SizeSelectionScreen().printMessage();
				orderToChange.setPizzaSize(size);
				ServiceLayer.getSingleton().changeOrder(orderToChange);
				break;
			}
			case 2: {
				List<Topping> toppings = (List<Topping>) new ToppingSelectionScreen().printMessage();
				orderToChange.setToppings(toppings);
				ServiceLayer.getSingleton().changeOrder(orderToChange);
				break;
			}
			case 3: {
				PaymentMethod paymentMethod = (PaymentMethod) new PaymentMethodScreen().printMessage();
				orderToChange.setPaymentMethod(paymentMethod);
				ServiceLayer.getSingleton().changeOrder(orderToChange);
				break;
			}
			case 4: {
				return null;
			}
			default: {
				return null;
			}
			}
			
			System.out.println("Would you like to change something else? \n1) Yes\n2)No");
			choice = scanner.nextInt();
			scanner.nextLine();
			verifyInput(choice == 1 || choice == 2);
			if(choice == 1){
				printMessage();
			} else {
				return null;
			}
			
		} catch(BadInputException e){
			System.out.println("Invalid input. try again.");
			printMessage();
		} catch (InputMismatchException e){
			scanner.nextLine();
			System.out.println("Bad input. Try again.");
		} catch (NotSignedInException e){
			System.out.println("You have to be signed in to change an order!");
			return null;
		}
		return null;
	}

	@Override
	public void commitMessage(ScreenContext ctx) {
		try{
			printMessage();
			ctx.setScreen(ctx.getPreviousScreen());
		} catch (Exception e){
			System.out.println("Something went wrong");
			ctx.setScreen(ctx.getPreviousScreen());
		}
	}

}
