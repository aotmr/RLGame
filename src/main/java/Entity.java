import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.ObjDoubleConsumer;

/**
 * Abstract element of an EntityList; this could be a proxy object.
 */
public abstract class Entity {
    public abstract String getName();

    public abstract void setOnUpdate(ObjDoubleConsumer<Entity> func);

    public abstract void setOnDraw(BiConsumer<Entity, SpriteList> func);

    public abstract ObjDoubleConsumer<Entity> getOnUpdate();

    public abstract BiConsumer<Entity, SpriteList> getOnDraw();

    public abstract float getX();

    public abstract void setX(float value);

    public abstract float getY();

    public abstract void setY(float value);

    public abstract float getVelocityX();

    public abstract void setVelocityX(float value);

    public abstract float getVelocityY();

    public abstract void setVelocityY(float value);

    public void doUpdate(double dt) {
        getOnUpdate().accept(this, dt);
    }

    public void doDraw(SpriteList outSprites) {
        getOnDraw().accept(this, outSprites);
    }

    public void moveBy(float dx, float dy) {
        setX(getX() + dx);
        setY(getY() + dy);
    }

    public void accelBy(float ax, float ay) {
        setVelocityX(getVelocityX() + ax);
        setVelocityY(getVelocityY() + ay);
    }

    public void clampVelocity(float limit) {
        float vx = getVelocityX();
        float vy = getVelocityY();
        float norm = (float)Math.sqrt(vx * vx + vy * vy);
        if (norm <= 0.001 || norm <= limit)
            return;
        float scale = limit / norm;
        setVelocityX(vx * scale);
        setVelocityY(vy * scale);
    }

    @Override
    public String toString() {
        return "Entity{" +
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
