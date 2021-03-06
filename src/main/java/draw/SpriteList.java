package draw;

import com.raylib.Raylib;

import java.util.AbstractList;

public class SpriteList extends AbstractList<SpriteList.SpriteData> {
    private static final int OBJECTSTRIDE = ObjectItem.values().length;
    private static final int FLOATSTRIDE = FloatItem.values().length;
    private final float[] floatData;
    private final Object[] objectData;
    int size;
    int capacity;
    public SpriteList(int capacity) {
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
        floatData[FLOATSTRIDE * index + FloatItem.X.ordinal()] = data.x;
        floatData[FLOATSTRIDE * index + FloatItem.Y.ordinal()] = data.y;
        floatData[FLOATSTRIDE * index + FloatItem.Width.ordinal()] = data.width;
        floatData[FLOATSTRIDE * index + FloatItem.Height.ordinal()] = data.height;
        floatData[FLOATSTRIDE * index + FloatItem.OriginX.ordinal()] = data.offsetX;
        floatData[FLOATSTRIDE * index + FloatItem.OriginY.ordinal()] = data.offsetY;
        floatData[FLOATSTRIDE * index + FloatItem.Rot.ordinal()] = data.rot;
        objectData[OBJECTSTRIDE * index + ObjectItem.Texture.ordinal()] = data.texture;
        objectData[OBJECTSTRIDE * index + ObjectItem.Source.ordinal()] = data.source;
        objectData[OBJECTSTRIDE * index + ObjectItem.Color.ordinal()] = data.color;
        return true;
    }

    @Override
    public SpriteData get(int index) {
        return new SpriteData(
                floatData[FLOATSTRIDE * index + FloatItem.X.ordinal()],
                floatData[FLOATSTRIDE * index + FloatItem.Y.ordinal()],
                floatData[FLOATSTRIDE * index + FloatItem.Width.ordinal()],
                floatData[FLOATSTRIDE * index + FloatItem.Height.ordinal()],
                floatData[FLOATSTRIDE * index + FloatItem.OriginX.ordinal()],
                floatData[FLOATSTRIDE * index + FloatItem.OriginY.ordinal()],
                floatData[FLOATSTRIDE * index + FloatItem.Rot.ordinal()],
                (Raylib.Texture) objectData[OBJECTSTRIDE * index + ObjectItem.Texture.ordinal()],
                (Raylib.Rectangle) objectData[OBJECTSTRIDE * index + ObjectItem.Source.ordinal()],
                (Raylib.Color) objectData[OBJECTSTRIDE * index + ObjectItem.Color.ordinal()]);
    }

    public void drawAll() {
        var dest = new Raylib.Rectangle();
        var origin = new Raylib.Vector2();

        for (SpriteData data : this) {
            dest.x(data.x)
                    .y(data.y)
                    .width(data.width)
                    .height(data.height);
            origin.x(data.offsetX)
                    .y(data.offsetY);
//            Raylib.DrawTexturePro(data.texture, dest, data.source, origin, data.rot, data.color);
            Raylib.DrawRectanglePro(dest, origin, data.rot, data.color);
        }
    }

    private enum FloatItem {
        X,
        Y,
        Width,
        Height,
        OriginX,
        OriginY,
        Rot,
    }

    private enum ObjectItem {
        Texture,
        Source,
        Color,
    }

    public record SpriteData(
            float x,
            float y,
            float width,
            float height,
            float offsetX,
            float offsetY,
            float rot,
            Raylib.Texture texture,
            Raylib.Rectangle source,
            Raylib.Color color) {
    }

}
