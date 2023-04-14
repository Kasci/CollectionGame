package sk.kasci.map;

import sk.kasci.map.object.ObjectColor;
import sk.kasci.map.object.ObjectOrientation;
import sk.kasci.map.object.ObjectType;

public class MapObject {

    private ObjectColor color;
    private ObjectType type;
    private ObjectOrientation orientation;

    public MapObject(ObjectType type) {
        this(type, ObjectColor.NONE, ObjectOrientation.NONE);
    }

    public MapObject(ObjectType type, ObjectColor color) {
        this(type, color, ObjectOrientation.NONE);
    }

    public MapObject(ObjectType type, ObjectColor color, ObjectOrientation orientation) {
        this.type = type;
        this.color = color;
        this.orientation = orientation;
    }

    public ObjectColor getColor() {
        return color;
    }

    public void setColor(ObjectColor color) {
        this.color = color;
    }

    public ObjectType getType() {
        return type;
    }

    public void setType(ObjectType type) {
        this.type = type;
    }

    public ObjectOrientation getOrientation() {
        return orientation;
    }

    public void setOrientation(ObjectOrientation orientation) {
        this.orientation = orientation;
    }
}

