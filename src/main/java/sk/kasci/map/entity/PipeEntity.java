package sk.kasci.map.entity;

import sk.kasci.map.entity.interfaces.InputtingEntity;
import sk.kasci.map.entity.interfaces.OutputtingEntity;

public class PipeEntity extends ObjectEntity implements InputtingEntity, OutputtingEntity {

    private ObjectEntity previous;
    private int time;

    public PipeEntity(ObjectEntity previous) {
        this.previous = previous;
    }

    @Override
    public void update(int delta) {
        time += delta;
        if (time >= 1000) {
            if (previous != null && previous.getCapacity() > 0) {
                previous.addCapacity(-1);
                addCapacity(1);
            }
            time -= 1000;
        }
    }
}
