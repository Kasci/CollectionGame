package sk.kasci.map.generator;

import sk.kasci.map.MapObject;
import sk.kasci.utils.PerlinNoise;

import static sk.kasci.map.MapObject.*;

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

    public static MapObject convert(double in) {
        int i = (int) (in * 5) + 5;
        if (i < 3) return RED;
        else if (i > 8) return BLUE;
        else return EMPTY;
    }
}
