package sk.kasci.input;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import static sk.kasci.input.InputValue.*;
import static sk.kasci.input.InputValue.NONE;

public class InputHandler {

    public InputHandler() {}

    public InputValue handleInput(KeyStroke keyStroke) {
        if (keyStroke == null) {
            return NONE;
        }
        KeyType keyType = keyStroke.getKeyType();
        switch (keyType) {
            case ArrowUp:
                return UP;
            case ArrowDown:
                return DOWN;
            case ArrowLeft:
                return LEFT;
            case ArrowRight:
                return RIGHT;
            case Character: {
                Character character = keyStroke.getCharacter();
                switch (character) {
                    case 'q': return QUIT;
                    case 'd': return DELETE;
                    case 'm': return ADD_MINER;
                    case 'p': return ADD_PIPE;
                    default: return NONE;
                }
            }
            default:
                return NONE;
        }
    }
}
