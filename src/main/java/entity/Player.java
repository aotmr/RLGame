package entity;

import com.raylib.Jaylib;
import com.raylib.Raylib;
import draw.SpriteList;

public class Player {
    private Player() {
        throw new AssertionError("Player class may not be instantiated");
    }

    private static void update(Entity entity, double dt) {
        final int WALKSPEED = 100;
        final float FRICTION = 0.3f;

        boolean keyLeft = Raylib.IsKeyDown(Raylib.KEY_LEFT);
        boolean keyRight = Raylib.IsKeyDown(Raylib.KEY_RIGHT);
        boolean keyUp = Raylib.IsKeyDown(Raylib.KEY_UP);
        boolean keyDown = Raylib.IsKeyDown(Raylib.KEY_DOWN);

        // The directional keys control a virtual joystick
        float dirX = 0;
        float dirY = 0;
        if (keyLeft) dirX -= 1;
        if (keyRight) dirX += 1;
        if (keyUp) dirY -= 1;
        if (keyDown) dirY += 1;

        if (dirX != 0 || dirY != 0) {
            float rnorm = 1 / (float)Math.sqrt(dirX * dirX + dirY * dirY);
            dirX *= rnorm;
            dirY *= rnorm;

            // If the stick is tilted, accelerate the in that direction
            entity.accelBy(dirX * WALKSPEED * FRICTION, dirY * WALKSPEED * FRICTION);
            entity.clampVelocity(WALKSPEED);
        } else {
            // Otherwise, slow the player down
            float vx = entity.getVelocityX();
            float vy = entity.getVelocityY();
            entity.accelBy(-FRICTION * vx, -FRICTION * vy);
        }
    }

    private static void draw(Entity entity, SpriteList spriteList) {
        spriteList.add(new SpriteList.SpriteData(
                entity.getX(),
                entity.getY(),
                16, 32,
                0, 0,
                0,
                null,
                null,
                Jaylib.VIOLET));
    }

    public static Entity create(EntityList parent) {
        return parent.create("Player")
                .setOnUpdate(Player::update)
                .setOnDraw(Player::draw);
    }
}
