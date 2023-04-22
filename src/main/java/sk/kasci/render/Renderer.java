package sk.kasci.render;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import sk.kasci.cursor.Cursor;
import sk.kasci.map.Map;
import sk.kasci.map.MapChunk;
import sk.kasci.map.MapObject;
import sk.kasci.map.object.ObjectType;

public class Renderer {

    private static final int xOff = 5;
    private static final int yOff = 5;
    private static final int size = 32;

    private int width = 2*size;
    private int height = size;

    private Screen screen;
    private TextGraphics g;

    public Renderer(Screen screen, TextGraphics g) {
        this.screen = screen;
        this.g = g;

    }

    public void render(Map map, Cursor cursor) {
        g.drawRectangle(new TerminalPosition(xOff-1, yOff-1), new TerminalSize(width+2, height+2 ), '+');
        TerminalPosition offsetPosition = new TerminalPosition(xOff, yOff);
        TerminalPosition centerPosition = new TerminalPosition(size + 1, ((size + 1) / 2));

        screen.setCursorPosition(centerPosition.plus(offsetPosition));

        TerminalPosition cursorPosition = new TerminalPosition(cursor.getX(), cursor.getY());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                TerminalPosition tmp = cursorPosition.minus(centerPosition).plus(new TerminalPosition(x,y));
                MapObject object = map.getObject(tmp.getColumn(), tmp.getRow());
                MapObject surfaceObject = map.getSurfaceObject(tmp.getColumn(), tmp.getRow());

                TerminalPosition terminalPosition = new TerminalPosition(x, y).plus(offsetPosition);
                if (surfaceObject.getType() != ObjectType.EMPTY) {
                    g.setCharacter(terminalPosition, RenderFactory.factory(surfaceObject));
                } else {
                    g.setCharacter(terminalPosition, RenderFactory.factory(object));
                }
            }
        }

        int infoX = xOff + 2*size + 5;
        int infoY = yOff;
        g.putString(new TerminalPosition(infoX, infoY), String.format("Cursor %2d, %2d", cursor.getX(), cursor.getY()));

        MapObject object = map.getObject(cursorPosition.getColumn(), cursorPosition.getRow());
        g.putString(new TerminalPosition(infoX, infoY + 2), String.format("Field %s %s %s", object.getType(), object.getColor(), object.getOrientation()));
        MapObject surfaceObject = map.getSurfaceObject(cursorPosition.getColumn(), cursorPosition.getRow());
        g.putString(new TerminalPosition(infoX, infoY + 3), String.format("Surface %s %s %s", surfaceObject.getType(), surfaceObject.getColor(), surfaceObject.getOrientation()));
        if (surfaceObject.getEntity() != null) {
            g.putString(new TerminalPosition(infoX, infoY + 5), String.format("Capacity %d", surfaceObject.getEntity().getCapacity()));
        }

    }

}
