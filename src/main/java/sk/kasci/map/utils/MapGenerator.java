package sk.kasci.map.utils;

import sk.kasci.map.MapObject;
import sk.kasci.map.object.ObjectColor;
import sk.kasci.map.object.ObjectType;
import sk.kasci.utils.PerlinNoise;

public class MapGenerator {

    private static final double SEED = 0.2;

    public static MapObject[][] generate(int X, int Y, int size) {
        PerlinNoise perlin = new PerlinNoise(SEED);
        MapObject[][] map = new MapObject[size][size];
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                double noise = perlin.noise(x + X, y + Y);
                map[y][x] = convert(noise);
            }
        }
        return map;
    }

    public static MapObject[][] generateSurface(int X, int Y, int size) {
        MapObject[][] surface = new MapObject[size][size];
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                surface[y][x] = new MapObject(ObjectType.EMPTY);
            }
        }
        return surface;
    }

    public static MapObject convert(double in) {
        int i = (int) (in * 5) + 5;
        if (i < 3) return new MapObject(ObjectType.ORE, ObjectColor.RED);
        else if (i > 8) return new MapObject(ObjectType.ORE, ObjectColor.BLUE);
        else return new MapObject(ObjectType.EMPTY);
    }
}
