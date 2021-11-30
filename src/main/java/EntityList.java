import java.util.AbstractList;
import java.util.function.BiConsumer;
import java.util.function.ObjDoubleConsumer;

/**
 * A list of entities backed by contiguous primitive arrays, where possible.
 */
public class EntityList extends AbstractList<Entity> {
    private static final int FLOATSTRIDE = FloatItem.values().length;
    private static final int OBJECTSTRIDE = ObjectItem.values().length;
    private final float[] floatData;
    private final Object[] objectData;
    private final int capacity;
    private int size;

    EntityList(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.floatData = new float[FLOATSTRIDE * capacity];
        this.objectData = new Object[OBJECTSTRIDE * capacity];
    }

    private void set(int index, ObjectItem item, Object value) {
        objectData[OBJECTSTRIDE * index + item.ordinal()] = value;
    }

    private void set(int index, FloatItem item, float value) {
        floatData[FLOATSTRIDE * index + item.ordinal()] = value;
    }

    private Object get(int index, ObjectItem item) {
        return objectData[OBJECTSTRIDE * index + item.ordinal()];
    }

    private float get(int index, FloatItem item) {
        return floatData[FLOATSTRIDE * index + item.ordinal()];
    }

    public Entity create(String name) {
        if (size >= capacity)
            throw new IndexOutOfBoundsException();
        int index = size++;
        set(index, ObjectItem.Name, name);
        return get(index);
    }

    @Override
    public Entity get(int index) {
        return new EntityProxy(this, index);
    }

    @Override
    public int size() {
        return size;
    }

    private enum FloatItem {
        X,
        Y,
        VelocityX,
        VelocityY,
    }

    private enum ObjectItem {
        Name,
        OnUpdate,
        OnDraw,
    }

    private static final class EntityProxy extends Entity {
        private final EntityList parent;
        private final int index;

        private EntityProxy(EntityList parent, int index) {
            this.parent = parent;
            this.index = index;
        }

        @Override
        public String getName() {
            return (String) parent.get(index, ObjectItem.Name);
        }

        @Override
        public ObjDoubleConsumer<Entity> getOnUpdate() {
            return (ObjDoubleConsumer<Entity>) parent.get(index, ObjectItem.OnUpdate);
        }

        @Override
        public void setOnUpdate(ObjDoubleConsumer<Entity> func) {
            parent.set(index, ObjectItem.OnUpdate, func);
        }

        @Override
        public BiConsumer<Entity, SpriteList> getOnDraw() {
            return (BiConsumer<Entity, SpriteList>) parent.get(index, ObjectItem.OnDraw);
        }

        @Override
        public void setOnDraw(BiConsumer<Entity, SpriteList> func) {
            parent.set(index, ObjectItem.OnUpdate, func);
        }

        @Override
        public float getX() {
            return parent.get(index, FloatItem.X);
        }

        @Override
        public void setX(float value) {
            parent.set(index, FloatItem.X, value);
        }

        @Override
        public float getY() {
            return parent.get(index, FloatItem.Y);
        }

        @Override
        public void setY(float value) {
            parent.set(index, FloatItem.Y, value);
        }

        @Override
        public float getVelocityX() {
            return parent.floatData[FLOATSTRIDE * index + 2];
        }

        @Override
        public void setVelocityX(float value) {
            parent.floatData[FLOATSTRIDE * index + 2] = value;
        }

        @Override
        public float getVelocityY() {
            return parent.floatData[FLOATSTRIDE * index + 3];
        }

        @Override
        public void setVelocityY(float value) {
            parent.floatData[FLOATSTRIDE * index + 3] = value;
        }
    }

}
