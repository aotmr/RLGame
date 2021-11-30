package entity;

import draw.SpriteList;

import java.util.function.BiConsumer;
import java.util.function.ObjDoubleConsumer;

/**
 * Abstract element of an entity.EntityList; this could be a proxy object.
 */
public abstract class Entity {
    public abstract String getName();

    public abstract Entity setOnUpdate(ObjDoubleConsumer<Entity> func);

    public abstract Entity setOnDraw(BiConsumer<Entity, SpriteList> func);

    public abstract ObjDoubleConsumer<Entity> getOnUpdate();

    public abstract BiConsumer<Entity, SpriteList> getOnDraw();

    public abstract float getX();

    public abstract Entity setX(float value);

    public abstract float getY();

    public abstract Entity setY(float value);

    public abstract float getVelocityX();

    public abstract Entity setVelocityX(float value);

    public abstract float getVelocityY();

    public abstract Entity setVelocityY(float value);

    public void doUpdate(double dt) {
        getOnUpdate().accept(this, dt);
    }

    public void doDraw(SpriteList outSprites) {
        getOnDraw().accept(this, outSprites);
    }

    public Entity moveBy(float dx, float dy) {
        setX(getX() + dx);
        setY(getY() + dy);
        return this;
    }

    public Entity accelBy(float ax, float ay) {
        setVelocityX(getVelocityX() + ax);
        setVelocityY(getVelocityY() + ay);
        return this;
    }

    public Entity clampVelocity(float limit) {
        float vx = getVelocityX();
        float vy = getVelocityY();
        float norm = (float)Math.sqrt(vx * vx + vy * vy);
        if (norm >= 0.001 && norm >= limit) {
            float scale = limit / norm;
            setVelocityX(vx * scale);
            setVelocityY(vy * scale);
        }
        return this;
    }

    public void updatePosition(float dt) {
        var vx = getVelocityX();
        var vy = getVelocityY();
        moveBy(vx * dt, vy * dt);
    }

    @Override
    public String toString() {
        return "entity.Entity{" +
                "name=" + getName() +
                ", x=" + getX() +
                ", y=" + getY() +
                ", velocityX=" + getVelocityX() +
                ", velocityY=" + getVelocityY() +
                ", onUpdate=" + getOnUpdate() +
                ", onDraw=" + getOnDraw() +
                "}";
    }
}
