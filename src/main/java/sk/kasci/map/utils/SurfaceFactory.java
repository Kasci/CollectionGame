package sk.kasci.map.utils;

import sk.kasci.map.Map;
import sk.kasci.map.MapChunk;
import sk.kasci.map.MapObject;
import sk.kasci.map.entity.MinerEntity;
import sk.kasci.map.entity.ObjectEntity;
import sk.kasci.map.entity.PipeEntity;
import sk.kasci.map.entity.interfaces.OutputtingEntity;
import sk.kasci.map.object.ObjectColor;
import sk.kasci.map.object.ObjectOrientation;
import sk.kasci.map.object.ObjectType;

public class SurfaceFactory {

    public Map map;

    public SurfaceFactory(Map map) {
        this.map = map;
    }
    public void addSurfaceObject(int x, int y, ObjectType type) {
        int chunkX = this.map.chunkCoord(x);
        int chunkY = this.map.chunkCoord(y);
        MapChunk chunk = this.map.getChunk(chunkX, chunkY);
        int X = x - (chunkX*MapChunk.SIZE);
        int Y = y - (chunkY*MapChunk.SIZE);
        chunk.setSurfaceObject(X, Y, getObject(x, y, type));
    }

    private MapObject getObject(int x, int y, ObjectType type) {
        switch (type) {
            case MINER: {
                ObjectColor color = this.map.getObject(x,y).getColor();
                return new MapObject(type, color, ObjectOrientation.NONE, new MinerEntity());
            }
            case PIPE: {
                ObjectColor color = ObjectColor.NONE;
                ObjectOrientation orientation = ObjectOrientation.NONE;
                MapObject top = this.map.getSurfaceObject(x, y-1);
                MapObject right = this.map.getSurfaceObject(x+1, y);
                MapObject bottom = this.map.getSurfaceObject(x, y+1);
                MapObject left = this.map.getSurfaceObject(x-1, y);
                ObjectEntity previous = null;
                if (top.getEntity() instanceof OutputtingEntity) {
                    color = top.getColor();
                    orientation = ObjectOrientation.DOWN;
                    previous = top.getEntity();
                } else if (right.getEntity() instanceof OutputtingEntity) {
                    color = right.getColor();
                    orientation = ObjectOrientation.LEFT;
                    previous = right.getEntity();
                } else if (bottom.getEntity() instanceof OutputtingEntity) {
                    color = bottom.getColor();
                    orientation = ObjectOrientation.UP;
                    previous = bottom.getEntity();
                } else if (left.getEntity() instanceof OutputtingEntity) {
                    color = left.getColor();
                    orientation = ObjectOrientation.RIGHT;
                    previous = left.getEntity();
                }
                return new MapObject(type, color, orientation, new PipeEntity(previous));
            }
            default: return new MapObject(ObjectType.EMPTY);
        }
    }
}
