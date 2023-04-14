package sk.kasci.map;

import java.util.HashMap;

public class Map {

    private HashMap<String, MapChunk> mapChunks;

    private static final int RENDER_DISTANCE = 3;

    public Map() {
        mapChunks = new HashMap<>();
        addChunk(0,0);
        generate(0,0);
    }

    private String format(int x, int y) {
        return String.format("%05d|%05d", x, y);
    }

    private void addChunk(int x, int y) {
        mapChunks.put(format(x, y), new MapChunk(x,y));
    }

    public MapChunk get(int x, int y) {
        return mapChunks.get(format(x, y));
    }

    public MapObject getObject(int x, int y) {
        int X = chunkCoord(x);
        int Y = chunkCoord(y);
        MapChunk chunk = get(X, Y);
        return chunk.getObject(x - (X*MapChunk.SIZE), y - (Y*MapChunk.SIZE));
    }

    public MapObject getSurfaceObject(int x, int y) {
        int X = chunkCoord(x);
        int Y = chunkCoord(y);
        MapChunk chunk = get(X, Y);
        return chunk.getSurfaceObject(x - (X*MapChunk.SIZE), y - (Y*MapChunk.SIZE));
    }

    public void generate(int X, int Y) {
        MapChunk c = mapChunks.get(format(X,Y));
        if (c.isDistanceGenerated()) return;
        for (int x = -RENDER_DISTANCE; x < RENDER_DISTANCE+1; x++) {
            for (int y = -RENDER_DISTANCE; y < RENDER_DISTANCE+1; y++) {
                if (!mapChunks.containsKey(format(X+x, Y+y)))
                    addChunk(X+x,Y+y);
            }
        }
        c.setDistanceGenerated(true);
    }

    public static int chunkCoord(int i) {
        if (i<0) return ((i+1) / MapChunk.SIZE) - 1;
        return i / MapChunk.SIZE;
    }

    public void addMiner(int x, int y) {
        int X = chunkCoord(x);
        int Y = chunkCoord(y);
        MapChunk chunk = get(X, Y);
        chunk.addMiner(x - (X*MapChunk.SIZE), y - (Y*MapChunk.SIZE));
    }
}
