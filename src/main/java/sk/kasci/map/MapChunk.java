package sk.kasci.map;

import sk.kasci.map.utils.MapGenerator;
import sk.kasci.map.object.ObjectColor;
import sk.kasci.map.object.ObjectType;

public class MapChunk {

    public static final int SIZE = 16;

    private boolean distanceGenerated = false;

    private MapObject[][] map;
    private MapObject[][] surface;

    public MapChunk(int x, int y) {
        this.map = MapGenerator.generate(x*SIZE, y*SIZE, SIZE);
        this.surface = MapGenerator.generateSurface(x*SIZE, y*SIZE, SIZE);
    }

    public MapObject getObject(int x, int y) {
        return this.map[y][x];
    }

    public MapObject getSurfaceObject(int x, int y) {
        return this.surface[y][x];
    }

    public void setSurfaceObject(int x, int y, MapObject object) {
        this.surface[y][x] = object;
    }

    public boolean isDistanceGenerated() {
        return distanceGenerated;
    }

    public void setDistanceGenerated(boolean distanceGenerated) {
        this.distanceGenerated = distanceGenerated;
    }

    public void update(int delta) {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                MapObject o = this.surface[y][x];
                o.update(delta);
            }
        }
    }

    public void delete(int x, int y) {
        this.surface[y][x] = new MapObject(ObjectType.EMPTY);
    }

}
