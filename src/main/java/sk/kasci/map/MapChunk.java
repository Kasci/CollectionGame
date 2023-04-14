package sk.kasci.map;

import sk.kasci.map.generator.MapGenerator;
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

    public boolean isDistanceGenerated() {
        return distanceGenerated;
    }

    public void setDistanceGenerated(boolean distanceGenerated) {
        this.distanceGenerated = distanceGenerated;
    }

    public void addMiner(int x, int y) {
        ObjectColor c = getObject(x,y).getColor();
        this.surface[y][x] = new MapObject(ObjectType.MINER, c);
    }
}
