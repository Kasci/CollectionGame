package sk.kasci.map;

import sk.kasci.map.generator.MapGenerator;

public class MapChunk {

    public static final int SIZE = 16;

    private boolean distanceGenerated = false;

    private MapObject[][] map;
    private MapObject[][] surface;

    public MapChunk(int x, int y) {
        this.map = MapGenerator.generate(x*SIZE, y*SIZE, SIZE);
    }

    public MapObject getObject(int x, int y) {
        return this.map[y][x];
    }

    public boolean isDistanceGenerated() {
        return distanceGenerated;
    }

    public void setDistanceGenerated(boolean distanceGenerated) {
        this.distanceGenerated = distanceGenerated;
    }
}
