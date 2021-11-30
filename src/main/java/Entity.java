/**
 * Abstract element of an EntityList; this could be a proxy object.
 */
public abstract class Entity {
    abstract String getName();
    abstract float getX();
    abstract float getY();
    abstract float getVelocityX();
    abstract float getVelocityY();
    abstract void setX(float value);
    abstract void setY(float value);
    abstract void setVelocityX(float value);
    abstract void setVelocityY(float value);

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
                "}";
    }
}
