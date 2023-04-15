package sk.kasci;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import sk.kasci.cursor.Cursor;
import sk.kasci.input.InputHandler;
import sk.kasci.input.InputValue;
import sk.kasci.map.Map;
import sk.kasci.map.MapChunk;
import sk.kasci.render.Renderer;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

import static sk.kasci.input.InputValue.*;

public class Game {

    private static final int FPS = 60;
    private static final int REFRESH = 1000/FPS;

    private Screen screen;
    private TextGraphics textGraphics;

    private Instant lastLoop;

    private Map map;
    private Cursor cursor;

    private InputHandler inputHandler;
    private Renderer renderer;

    private boolean running = false;

    public Game(Screen screen) {
        this.screen = screen;
        this.textGraphics = this.screen.newTextGraphics();

        this.lastLoop = Instant.now();

        this.map = new Map();
        this.cursor = new Cursor();

        this.inputHandler = new InputHandler();
        this.renderer = new Renderer(this.screen, this.textGraphics);
    }

    public void start() {
        this.running = true;
        while (running) {
            Instant now = Instant.now();
            if (Duration.between(lastLoop, now).toMillis() > REFRESH) {
                render();
                InputValue i = input();
                update(i);
                this.lastLoop = now;
            }
        }
    }

    private void update(InputValue i) {
        switch (i) {
            case QUIT: this.running = false; break;
            case UP: this.cursor.move(0, -1); break;
            case DOWN: this.cursor.move(0, 1); break;
            case LEFT: this.cursor.move(-1, 0); break;
            case RIGHT: this.cursor.move(1, 0); break;
            case ADD_MINER: this.map.addMiner(this.cursor.getX(), this.cursor.getY());
        }
        map.generate(Map.chunkCoord(cursor.getX()), Map.chunkCoord(cursor.getY()));
    }

    private InputValue input() {
        try {
            KeyStroke keyStroke = this.screen.pollInput();
            return this.inputHandler.handleInput(keyStroke);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void render() {
        try {
            this.screen.clear();
            this.renderer.render(map, cursor);
            this.screen.refresh();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
