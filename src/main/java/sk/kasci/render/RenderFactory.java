package sk.kasci.render;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import sk.kasci.map.MapObject;

import sk.kasci.map.MapObject.*;

public class RenderFactory {

    private static final TextColor BLACK = new TextColor.RGB(20,20,20);
    private static final TextColor WHITE = new TextColor.RGB(225,225,225);
    public static TextCharacter factory(MapObject object) {
        char c = factoryCharacter(object);
        switch (object.getColor()) {
            case RED: return new TextCharacter(c, new TextColor.RGB(225,50,50), BLACK);
            case BLUE: return new TextCharacter(c, new TextColor.RGB(50,50,225), BLACK);
            case GREEN: return new TextCharacter(c, new TextColor.RGB(50,255,50), BLACK);
            case YELLOW: return new TextCharacter(c, new TextColor.RGB(225,225,50), BLACK);
            case MAGENTA: return new TextCharacter(c, new TextColor.RGB(225,50,225), BLACK);
            case CYAN: return new TextCharacter(c, new TextColor.RGB(50,225,225), BLACK);
            case WHITE: return new TextCharacter(c, new TextColor.RGB(225,225,225), BLACK);
            default: return new TextCharacter(c, WHITE, BLACK);
        }
    }

    private static char factoryCharacter(MapObject object) {
        switch (object.getType()) {
            case ORE: return '.';
            case MINER: return '@';
            case PIPE: {
                switch (object.getOrientation()) {
                    case LEFT: case RIGHT: return '-';
                    case UP: case DOWN: return '|';
                    default: return '+';
                }
            }
            default: return ' ';
        }
    }

}
