package entity;

import com.raylib.Jaylib;
import draw.SpriteList;

public class Player {
    private Player() {
        throw new AssertionError("Player class may not be instantiated");
    }

    public static Entity create(EntityList parent) {
        return parent.create("Player")
                .setOnUpdate((entity, dt) -> {
                })
                .setOnDraw((entity, spriteList) -> {
                    spriteList.add(new SpriteList.SpriteData(
                            entity.getX(),
                            entity.getY(),
                            20, 20,
                            0, 0,
                            0,
                            null,
                            null,
                            Jaylib.WHITE));
                });
    }
}
