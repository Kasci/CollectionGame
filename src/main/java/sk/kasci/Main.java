package sk.kasci;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
        defaultTerminalFactory.setInitialTerminalSize(new TerminalSize(150, 50));
        Terminal terminal = defaultTerminalFactory.createTerminal();
        Screen screen = new TerminalScreen(terminal);
        screen.startScreen();

        Game game = new Game(screen);
        game.start();

        screen.stopScreen();
    }
}
