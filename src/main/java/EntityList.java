import java.util.AbstractList;
import java.util.Objects;

public class EntityList extends AbstractList<Entity> {
    private static final int FLOATSTRIDE = 4;
    private static final int OBJECTSTRIDE = 2;

    private final float[] floatData;
    private final Object[] objectData;

    private int capacity;
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

    @Override
    public int size() {
        return size;
    }

    @SuppressWarnings("PointlessArithmeticExpression")
    private static final class EntityProxy extends Entity {
        private final EntityList parent;
        private final int index;

        private EntityProxy(EntityList parent, int index) {
            this.parent = parent;
            this.index = index;
        }

        @Override
        public String getName() {
            return (String) parent.objectData[OBJECTSTRIDE * index + 0];
        }

        @Override
        public float getX() {
            return parent.floatData[FLOATSTRIDE * index + 0];
        }

        @Override
        public void setX(float value) {
            parent.floatData[FLOATSTRIDE * index + 0] = value;
        }

        @Override
        public float getY() {
            return parent.floatData[FLOATSTRIDE * index + 1];
        }

        @Override
        public void setY(float value) {
            parent.floatData[FLOATSTRIDE * index + 1] = value;
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
