package state;

import com.raylib.Jaylib;
import com.raylib.Raylib;
import draw.SpriteList;
import entity.EntityList;
import entity.Player;

public class GameState implements State {
    EntityList entities = new EntityList(10);
    SpriteList sprites = new SpriteList(30);

    @Override
    public void setup() {
        Player.create(entities)
                .setX(25)
                .setY(37)
                .setVelocityX(8)
                .setVelocityY(3);
    }

    @Override
    public void update(float dt) {
        for (var e : entities) {
            e.move(dt);
            e.doUpdate(dt);
        }
    }

    @Override
    public void draw() {
        Raylib.ClearBackground(Jaylib.BLACK);
        sprites.clear();
        for (var e : entities)
            e.doDraw(sprites);
        sprites.drawAll();
    }

    @Override
    public void cleanup() {
        entities.clear();
        sprites.clear();
    }
}
