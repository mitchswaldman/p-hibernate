package ui;
import screen.*;
public class PresentationLayer {
	
	public static void main(String[] args) {
		ScreenContext ctx = new ScreenContext(new HomeScreen());
		while(true){
			ctx.showScreen();
		}
	}
}
