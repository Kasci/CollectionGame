package sk.kasci.map.entity;

public abstract class ObjectEntity {

    private int capacity = 0;
    private int maxCapacity = 0;
    public abstract void update(int delta);

    public int getCapacity() {
        return capacity;
    }

    public void addCapacity(int delta) {
        this.capacity += delta;
    }
}
