public interface Entity {
    String getName();
    float getX();
    float getY();
    float getVelocityX();
    float getVelocityY();
    void setX(float value);
    void setY(float value);
    void setVelocityX(float value);
    void setVelocityY(float value);

    default void moveBy(float dx, float dy) {
        setX(getX() + dx);
        setY(getY() + dy);
    }

    default void accelBy(float ax, float ay) {
        setVelocityX(getVelocityX() + ax);
        setVelocityY(getVelocityY() + ay);
    }

    default void clampVelocity(float limit) {
        float vx = getVelocityX();
        float vy = getVelocityY();
        float norm = (float)Math.sqrt(vx * vx + vy * vy);
        if (norm <= 0.001 || norm <= limit)
            return;
        float scale = limit / norm;
        setVelocityX(vx * scale);
        setVelocityY(vy * scale);
    }
}
