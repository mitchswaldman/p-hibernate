package screen;

import java.util.InputMismatchException;
import java.util.Scanner;

import entity.Customer;
import ui.ScreenContext;
import ui.ServiceLayer;

public class LogInScreen extends Screen{

	@Override
	public void commitMessage(ScreenContext ctx) {
		try {
			
			Customer c = (Customer) printMessage();
			if(ServiceLayer.getSingleton().logIn(c.getUserName(), c.getPassword())){
				System.out.println("Login successful.");
				ctx.setScreen(ctx.getPreviousScreen());
			} else {
				System.out.println("Login FAILED.");
				ctx.setScreen(ctx.getPreviousScreen());
			}
		} catch(Exception e) {
			System.out.println("Something went horribly wrong.");
			System.out.println(e.getLocalizedMessage());
			ctx.setScreen(ctx.getPreviousScreen());
		}
		
	}

	@Override
	public Object printMessage() {
		try {
			System.out.println("Enter username:");
			String username = scanner.nextLine();
			System.out.println("Enter password:");
			String password = scanner.nextLine();
			Customer c = new Customer();
			c.setUserName(username);
			c.setPassword(password);
			return c;
		} catch (InputMismatchException e){
			System.out.println("Bad input. Try again.");
			printMessage();
		} 
		return null;
	}

}
