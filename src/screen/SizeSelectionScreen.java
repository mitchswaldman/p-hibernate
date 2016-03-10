package screen;

import java.util.InputMismatchException;

import entity.PizzaSize;
import ui.BadInputException;
import ui.ScreenContext;

public class SizeSelectionScreen extends Screen {

	@Override
	public Object printMessage() {
		try {
			System.out.println("What size pizza would you like to order?");
			PizzaSize[] sizes = PizzaSize.values();
			for (int i = 0; i < sizes.length; i++) {
				System.out.println( (i+1) + ") " + sizes[i].toString());
			}
			int pizzaSize = scanner.nextInt();
			scanner.nextLine();
			verifyInput(pizzaSize > 0 && pizzaSize <= sizes.length);
			PizzaSize size = sizes[pizzaSize - 1];
			return size;
		} catch(BadInputException e){
			System.out.println("Bad input. " + e.getLocalizedMessage());
			System.out.println("Try again.");
			return printMessage();
		} catch(InputMismatchException e){
			System.out.println("Bad input. Try again.");
			scanner.nextLine();
			return printMessage();
		} catch(Exception e){
			return null;
		}
	}

	@Override
	public void commitMessage(ScreenContext ctx) {
		
	}

}
