import com.raylib.Raylib;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.AbstractList;

public class SpriteList extends AbstractList<SpriteList.Sprite> {
    public record Sprite(
            float x, float y, float w, float h,
            @NotNull Raylib.Color color,
            @Nullable Raylib.Texture texture) {}

    final int FDATA_ROWS = 4;
    final int ODATA_ROWS = 2;

    int size;
    int capacity;

    float[] fdata;
    Object[] odata;

    SpriteList(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.fdata = new float[FDATA_ROWS * capacity];
        this.odata = new Object[ODATA_ROWS * capacity];
    }

    @Override
    public Sprite get(int index) {
        return new Sprite(
                fdata[FDATA_ROWS * index + 0],
                fdata[FDATA_ROWS * index + 1],
                fdata[FDATA_ROWS * index + 2],
                fdata[FDATA_ROWS * index + 3],
                (Raylib.Color) odata[ODATA_ROWS * index + 0],
        (Raylib.Texture)odata[ODATA_ROWS * index + 1]);
    }

    @Override
    public Sprite set(int index, Sprite element) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        var last = get(index);
        fdata[FDATA_ROWS * index + 0] = element.x();
        fdata[FDATA_ROWS * index + 1] = element.y();
        fdata[FDATA_ROWS * index + 2] = element.w();
        fdata[FDATA_ROWS * index + 3] = element.h();
        odata[FDATA_ROWS * index + 0] = element.color();
        odata[FDATA_ROWS * index + 1] = element.texture();
        return last;
    }

    @Override
    public void add(int index, Sprite element) {
        if (index < size) {
            // shift elements to right
            System.arraycopy(
                    fdata, FDATA_ROWS * index,
                    fdata, FDATA_ROWS * (index + 1),
                    FDATA_ROWS * (size - index));
            System.arraycopy(
                    odata, ODATA_ROWS * index,
                    odata, ODATA_ROWS * (index + 1),
                    ODATA_ROWS * (size - index));
            size += 1;
        } else if (index == size) {
            size += 1;
        }
        set(index, element);
    }

    @Override
    public int size() {
        return size;
    }

    public void drawAll() {
        for (var sprite : this) {
            Raylib.DrawRectangle(
                    Math.round(sprite.x()),
                    Math.round(sprite.y()),
                    Math.round(sprite.w()),
                    Math.round(sprite.h()),
                    sprite.color());
        }
    }

}
