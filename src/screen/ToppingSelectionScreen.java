package screen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

import entity.Topping;
import ui.BadInputException;
import ui.ScreenContext;

public class ToppingSelectionScreen extends Screen {
	private List<Topping> toppings;
	private static final int MAX_TOPPINGS = 4;
	public ToppingSelectionScreen(){
		toppings = new ArrayList<>();
		toppings.add(new Topping("Pepperoni", 1.00));
		toppings.add(new Topping("Mushrooms", 0.75));
		toppings.add(new Topping("Onions", 0.50));
		toppings.add(new Topping("Sausage", 1.00));
		toppings.add(new Topping("Bacon", 1.25));
		toppings.add(new Topping("Extra cheese", 0.50));
		toppings.add(new Topping("Black olives", 0.60));
		toppings.add(new Topping("Green peppers", 0.70));
		toppings.add(new Topping("Pineapple", 1.00));
		toppings.add(new Topping("Spinach", 0.50));
	}
	
	@Override
	public Object printMessage() {
		try {
			System.out.println("What toppings would you like on your pizza?\nYou may select up to three.");
			System.out.println("Separate choices by commas, ex. 1, 4, 5");
			for (int i = 0; i < toppings.size(); i++) {
				Topping topping = toppings.get(i);
				System.out.println( (i + 1) + ") " + topping.getTopping() + " - " + topping.getPrice());
			}
			String input = scanner.nextLine();
			List<String> toppingChoices = Arrays.asList(input.split(","));
			List<Topping> selectedToppings = new ArrayList<>();
			verifyInput(toppingChoices.size() < MAX_TOPPINGS);
			for(String toppingChoice : toppingChoices){
				int tChoice = Integer.parseInt(toppingChoice);
				verifyInput(tChoice > 0 && tChoice <= toppings.size());
				selectedToppings.add(toppings.get(tChoice - 1));
			}
			return selectedToppings;
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
