package ui;
import screen.Screen;

public class ScreenContext {
	private Screen currentScreen;
	private Screen previousScreen;
	
	public ScreenContext(Screen screen){
		this.currentScreen = screen;
	}
	
	public void setScreen(Screen nextScreen){
		this.previousScreen = this.currentScreen;
		this.currentScreen = nextScreen;
	}
	
	public void showScreen(){
		currentScreen.commitMessage(this);
	}
	
	public Screen getPreviousScreen(){
		return previousScreen;
	}
}
