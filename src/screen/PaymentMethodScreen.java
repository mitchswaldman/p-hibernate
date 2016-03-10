package screen;

import java.util.InputMismatchException;

import entity.PaymentMethod;
import ui.BadInputException;
import ui.ScreenContext;

public class PaymentMethodScreen extends Screen {

	@Override
	public Object printMessage() {
		try{
			System.out.println("How would you like to pay?");
			PaymentMethod[] payMethods = PaymentMethod.values();
			for (int i = 0; i < payMethods.length; i++) {
				System.out.println( (i+1) + ") " + payMethods[i].toString());
			}
			int payChoice = scanner.nextInt();
			scanner.nextLine();
			verifyInput(payChoice > 0 && payChoice <= payMethods.length);
			PaymentMethod paymentMethod = payMethods[payChoice - 1];
			return paymentMethod;
		} catch(BadInputException e){
			System.out.println("Bad input. " + e.getLocalizedMessage());
			System.out.println("Try again.");
			return printMessage();
		} catch(InputMismatchException e){
			System.out.println("Bad input. Try again.");
			scanner.nextLine();
			return printMessage();
		}
	}

	@Override
	public void commitMessage(ScreenContext ctx) {
		// TODO Auto-generated method stub

	}

}
