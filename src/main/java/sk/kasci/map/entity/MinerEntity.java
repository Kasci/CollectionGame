package sk.kasci.map.entity;

import sk.kasci.map.entity.interfaces.OutputtingEntity;

public class MinerEntity extends ObjectEntity implements OutputtingEntity {

    private int time = 0;
    @Override
    public void update(int delta) {
        time += delta;
        if (time >= 1000) {
            addCapacity(1);
            time -= 1000;
        }
    }
}
