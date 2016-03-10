package screen;

import java.util.InputMismatchException;
import java.util.Scanner;

import ui.ScreenContext;

public class HomeScreen extends Screen {
	public HomeScreen(){
		super();
	}

	@Override
	public Object printMessage() {
		return null;
	}

	@Override
	public void commitMessage(ScreenContext ctx) {
		
		System.out.println("1) Sign up");
		System.out.println("2) Log in");
		System.out.println("3) Make order");
		System.out.println("4) Make discounted order");
		System.out.println("5) View all orders");
		System.out.println("6) Change an order");
		System.out.println("7) Cancel an order");
		System.out.println("8) Quit");
		try {
			int choice = scanner.nextInt();
			scanner.nextLine(); 
			switch (choice) {
			case 1: ctx.setScreen(new SignUpScreen()); break;
			case 2: ctx.setScreen(new LogInScreen()); break;
			case 3: ctx.setScreen(new MakeOrderScreen()); break;
			case 4: ctx.setScreen(new MakeDiscountOrderScreen()); break;
			case 5: ctx.setScreen(new ViewOrdersScreen()); break;
			case 6: ctx.setScreen(new ChangeOrderScreen()); break;
			case 7: ctx.setScreen(new CancelOrderScreen()); break;
			case 8: System.exit(0); break;
			default: System.exit(0); break;
			}
		} catch (InputMismatchException e){
			System.out.println("Bad input. Try again.");
			scanner.nextLine();
			commitMessage(ctx);
		} catch (Exception e) {
			System.out.println("Something went wrong.");
			System.exit(1);
		} 
		
	}
}
