package screen;

import java.util.InputMismatchException;
import java.util.Scanner;

import entity.Address;
import entity.Customer;
import ui.ScreenContext;
import ui.ServiceLayer;

public class SignUpScreen extends Screen {

	@Override
	public void commitMessage(ScreenContext ctx) {
		try{
			Customer customer = (Customer) printMessage();
			if(ServiceLayer.getSingleton().signIn(customer)){
				System.out.println("Successfully sign up!");
			} else{
				System.out.println("Failed to sign up.");
			}
			ctx.setScreen(ctx.getPreviousScreen());
		} catch(Exception e){
			System.out.println("Something went wrong. Going back to home screen.");
			System.out.println(e.getLocalizedMessage());
			ctx.setScreen(ctx.getPreviousScreen());
		}
	}

	@Override
	public Object printMessage() {
		try {
			System.out.println("Enter a username:");
			String username = scanner.nextLine();
			System.out.println("Enter a password:");
			String password = scanner.nextLine();
			System.out.println("Enter zipcode:");
			String zipcode = scanner.nextLine();
			System.out.println("Enter state:");
			String state = scanner.nextLine();
			System.out.println("Enter city:");
			String city = scanner.nextLine();
			System.out.println("Enter street address");
			String street = scanner.nextLine();
			Customer customer = new Customer();
			customer.setPassword(password);
			customer.setUserName(username);
			customer.setAddress(new Address(street, city, state, zipcode));
			return customer;
		} catch(InputMismatchException e){
			System.out.println("Bad input. Try again.");
			return printMessage();
		} catch(Exception e){
			return null;
		}
	}

}
