package sk.kasci.map;

import sk.kasci.map.entity.ObjectEntity;
import sk.kasci.map.object.ObjectColor;
import sk.kasci.map.object.ObjectOrientation;
import sk.kasci.map.object.ObjectType;

public class MapObject {

    private ObjectColor color;
    private ObjectType type;
    private ObjectOrientation orientation;

    private ObjectEntity entity;

    public MapObject(ObjectType type) {
        this(type, ObjectColor.NONE, ObjectOrientation.NONE, null);
    }

    public MapObject(ObjectType type, ObjectColor color) {
        this(type, color, ObjectOrientation.NONE, null);
    }

    public MapObject(ObjectType type, ObjectColor color, ObjectOrientation orientation) {
        this(type, color, orientation, null);
    }

    public MapObject(ObjectType type, ObjectColor color, ObjectOrientation orientation, ObjectEntity entity) {
        this.type = type;
        this.color = color;
        this.orientation = orientation;
        this.setEntity(entity);
    }

    public void update(int delta) {
        if (this.getEntity() != null) {
            this.getEntity().update(delta);
        }
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

    public ObjectEntity getEntity() {
        return entity;
    }

    public void setEntity(ObjectEntity entity) {
        this.entity = entity;
    }
}

