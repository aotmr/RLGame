public class EntityMgr {
    public final int CAPACITY = 100;
    private final EntityList entities = new EntityList(CAPACITY);

    public void updateAll(float dt) {
        for (var e:entities) {
            e.doUpdate(dt);

            var vx = e.getVelocityX();
            var vy = e.getVelocityY();
            e.moveBy(vx * dt, vy * dt);
        }
    }

    public void drawAll(SpriteList outSprites) {
        for (var e:entities)
            e.doDraw(outSprites);
    }
}
