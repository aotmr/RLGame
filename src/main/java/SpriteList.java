import com.raylib.Raylib;

import java.util.AbstractList;

public class SpriteList extends AbstractList<SpriteList.SpriteData> {
    private static final int OBJECTSTRIDE = 3;
    private static final int FLOATSTRIDE = 8;
    int size;
    int capacity;
    private final float[] floatData;
    private final Object[] objectData;

    SpriteList(int capacity) {
        this.capacity = capacity;
        floatData = new float[FLOATSTRIDE * capacity];
        objectData = new Object[OBJECTSTRIDE * capacity];
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(SpriteData data) {
        int index = size();
        ++size;
        floatData[FLOATSTRIDE * index] = data.x;
        floatData[FLOATSTRIDE * index + 1] = data.y;
        floatData[FLOATSTRIDE * index + 2] = data.w;
        floatData[FLOATSTRIDE * index + 3] = data.h;
        floatData[FLOATSTRIDE * index + 4] = data.ox;
        floatData[FLOATSTRIDE * index + 5] = data.oy;
        floatData[FLOATSTRIDE * index + 6] = data.r;
        objectData[OBJECTSTRIDE * index] = data.texture;
        objectData[OBJECTSTRIDE * index + 1] = data.source;
        objectData[OBJECTSTRIDE * index + 2] = data.color;
        return true;
    }

    @Override
    public SpriteData get(int index) {
        return new SpriteData(
                floatData[FLOATSTRIDE * index],
                floatData[FLOATSTRIDE * index + 1],
                floatData[FLOATSTRIDE * index + 2],
                floatData[FLOATSTRIDE * index + 3],
                floatData[FLOATSTRIDE * index + 4],
                floatData[FLOATSTRIDE * index + 5],
                floatData[FLOATSTRIDE * index + 6],
                (Raylib.Texture) objectData[OBJECTSTRIDE * index],
                (Raylib.Rectangle) objectData[OBJECTSTRIDE * index + 1],
                (Raylib.Color) objectData[OBJECTSTRIDE * index + 2]);
    }

    void drawAll() {
        var dest = new Raylib.Rectangle();
        var origin = new Raylib.Vector2();

        for (SpriteData data : this) {
            dest.x(data.x)
                    .y(data.y)
                    .width(data.w)
                    .height(data.h);
            origin.x(data.ox)
                    .y(data.oy);
            Raylib.DrawTexturePro(data.texture, dest, data.source, origin, data.r, data.color);
        }
    }

    public record SpriteData(
            float x,
            float y,
            float w,
            float h,
            float ox,
            float oy,
            float r,
            Raylib.Texture texture,
            Raylib.Rectangle source,
            Raylib.Color color) {
    }

}
