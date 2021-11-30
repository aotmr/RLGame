import java.util.AbstractList;

@SuppressWarnings("PointlessArithmeticExpression")
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

    @Override
    public Entity get(int index) {
        return new EntityProxy(this, index);
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

    @Override
    public int size() {
        return size;
    }

    public Entity create(String name) {
        int index = size++;
        set(index, ObjectItem.Name, name);
        return get(index);
    }

    enum FloatItem {
        X,
        Y,
        VelocityX,
        VelocityY,
    }

    enum ObjectItem {
        Name,
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
