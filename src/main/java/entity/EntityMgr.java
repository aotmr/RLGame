package entity;

import draw.SpriteList;

public class EntityMgr {
    public final int CAPACITY = 100;
    private final EntityList entities = new EntityList(CAPACITY);

    public void updateAll(float dt) {
        for (var e:entities) {
            e.doUpdate(dt);
            e.updatePosition(dt);
        }
    }

    public void drawAll(SpriteList outSprites) {
        for (var e:entities)
            e.doDraw(outSprites);
    }
}
