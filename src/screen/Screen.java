package screen;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import ui.BadInputException;
import ui.ScreenContext;
public abstract class Screen {
	protected static Scanner scanner = new Scanner(System.in);
	public abstract Object printMessage();
	public abstract void commitMessage(ScreenContext ctx);
	protected final void verifyInput(boolean bool) throws BadInputException{
		if(!bool) {throw new BadInputException();}
	}
}
