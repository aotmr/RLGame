public class EntityManager {
    private static final int FLOATSTRIDE = 4;
    private static final int OBJECTSTRIDE = 2;

    private float[] floatData;
    private Object[] objectData;

    public class EntityProxy implements Entity {
        private EntityManager parent;
        private int index;

        @Override
        public String getName() {
            return (String) parent.objectData[OBJECTSTRIDE * index + 0];
        }

        @Override
        public float getX() {
            return floatData[FLOATSTRIDE * index + 0];
        }

        @Override
        public void setX(float value) {
            floatData[FLOATSTRIDE * index + 0] = value;
        }

        @Override
        public float getY() {
            return floatData[FLOATSTRIDE * index + 1];
        }

        @Override
        public void setY(float value) {
            floatData[FLOATSTRIDE * index + 1] = value;
        }

        @Override
        public float getVelocityX() {
            return floatData[FLOATSTRIDE * index + 2];
        }

        @Override
        public void setVelocityX(float value) {
            floatData[FLOATSTRIDE * index + 2] = value;
        }

        @Override
        public float getVelocityY() {
            return floatData[FLOATSTRIDE * index + 3];
        }

        @Override
        public void setVelocityY(float value) {
            floatData[FLOATSTRIDE * index + 3] = value;
        }
    }

}
