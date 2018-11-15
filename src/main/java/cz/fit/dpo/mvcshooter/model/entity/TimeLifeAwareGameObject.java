package cz.fit.dpo.mvcshooter.model.entity;

public abstract class TimeLifeAwareGameObject extends GameObject {
    private long created = System.currentTimeMillis();

    public long getLifetime() {
        return System.currentTimeMillis() - created;
    }
}
