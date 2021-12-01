package entity;

import com.raylib.Jaylib;
import draw.SpriteList;

public class Player {
    private Player() {
        throw new AssertionError("Player class may not be instantiated");
    }

    private static void update(Entity entity, double dt) {

    }

    private static void draw(Entity entity, SpriteList spriteList) {
        spriteList.add(new SpriteList.SpriteData(
                entity.getX(),
                entity.getY(),
                20, 20,
                0, 0,
                0,
                null,
                null,
                Jaylib.WHITE));
    }

    public static Entity create(EntityList parent) {
        return parent.create("Player")
                .setOnUpdate(Player::update)
                .setOnDraw(Player::draw);
    }
}
