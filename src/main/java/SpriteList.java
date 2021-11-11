import com.raylib.Raylib;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Comparator;

@SuppressWarnings("PointlessArithmeticExpression")
public class SpriteList extends AbstractList<SpriteList.Sprite> {
    final int FDATA_ROWS = 4;
    final int ODATA_ROWS = 3;
    private int size;
    private int capacity;
    private float[] fdata;
    // TODO: I would rather implement this as parallel arrays of JNI objects
    Object[] odata;
    // Cached sprite order for drawAllInOrder
    private boolean drawCacheValid = false;
    private Sprite[] drawCache = new Sprite[0];

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
                (Raylib.Texture) odata[ODATA_ROWS * index + 1],
                (Raylib.Rectangle) odata[ODATA_ROWS * index + 2]);
    }

    @Override
    public Sprite set(int index, Sprite element) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        drawCacheValid = false;
        var last = get(index);
        fdata[FDATA_ROWS * index + 0] = element.x();
        fdata[FDATA_ROWS * index + 1] = element.y();
        fdata[FDATA_ROWS * index + 2] = element.w();
        fdata[FDATA_ROWS * index + 3] = element.h();
        odata[FDATA_ROWS * index + 0] = element.color();
        odata[FDATA_ROWS * index + 1] = element.texture();
        odata[FDATA_ROWS * index + 2] = element.rectangle();
        return last;
    }

    @Override
    public void add(int index, Sprite element) {
        if (index < 0 || index > size + 1 || index > capacity)
            throw new IndexOutOfBoundsException();
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
        for (var sprite : this)
            sprite.draw();
    }

    public void drawAllInOrder(Comparator<Sprite> comparator) {
        if (!drawCacheValid) {
            drawCache = this.toArray(drawCache);
            Arrays.sort(drawCache, comparator);
            drawCacheValid = true;
        }
        for (var sprite : drawCache) {
            sprite.draw();
        }
    }

    public record Sprite(
            float x, float y, float w, float h,
            @NotNull Raylib.Color color,
            @Nullable Raylib.Texture texture,
            @NotNull Raylib.Rectangle rectangle) {
        void draw() {
            var destRectangle = new Raylib.Rectangle().x(x).y(y).width(w).height(h);
            if (texture == null) {
                Raylib.DrawRectanglePro(destRectangle, Raylib.Vector2Zero(), 0, color);
            } else {
                Raylib.DrawTexturePro(
                        texture,
                        destRectangle,
                        rectangle,
                        Raylib.Vector2Zero(), 0,
                        color);
            }
        }
    }

}
