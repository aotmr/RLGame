import com.raylib.Raylib;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * A two-dimensional array of Tiledefs.
 * <p>
 * This appears as a collection of TiledefWithCoords, so iterating through
 * a Tilemap also gives access to a Tiledef's location in the map.
 */
public class Tilemap extends AbstractCollection<Tilemap.TiledefWithCoords> {
    final private int width;
    final private int height;
    final private @Nullable Tiledef[] chunk;

    /**
     * Constructs a tilemap with the given dimensions.
     */
    public Tilemap(int width, int height) {
        if (width < 0) throw new IllegalArgumentException();
        if (height < 0) throw new IllegalArgumentException();
        this.width = width;
        this.height = height;
        this.chunk = new Tiledef[Math.multiplyExact(width, height)]; // throws if product overflows
    }

    public @Nullable Tiledef get(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) throw new IndexOutOfBoundsException();
        return chunk[y * width + x];
    }

    public void set(int x, int y, @Nullable Tiledef tiledef) {
        if (x < 0 || x >= width || y < 0 || y >= height) throw new IndexOutOfBoundsException();
        chunk[y * width + x] = tiledef;
    }

    @Override
    public boolean add(@NotNull TiledefWithCoords tiledefCoords) {
        Tiledef last = get(tiledefCoords.x(), tiledefCoords.y());
        boolean changed = Objects.equals(last, tiledefCoords.tiledef);
        set(tiledefCoords.x(), tiledefCoords.y(), tiledefCoords.tiledef());
        return changed;
    }

    @Override
    public int size() {
        return chunk.length;
    }

    @Override
    public Iterator<TiledefWithCoords> iterator() {
        class TiledefCoordsIterator implements Iterator<TiledefWithCoords> {
            final Tilemap tilemap;
            int x = 0;
            int y = 0;

            TiledefCoordsIterator(@NotNull Tilemap tilemap) {
                this.tilemap = tilemap;
            }

            @Override
            public boolean hasNext() {
                return y < tilemap.height;
            }

            @Override
            public TiledefWithCoords next() {
                var tiledef = tilemap.get(x, y);
                var tiledefCoords = new TiledefWithCoords(x, y, tilemap.get(x, y));
                if (++x >= width) {
                    x = 0;
                    ++y;
                }
                return tiledefCoords;
            }

            @Override
            public void remove() {
                tilemap.set(x, y, null);
            }
        }
        return new TiledefCoordsIterator(this);
    }

    public record Tiledef(@NotNull Raylib.Color color) {
    }

    public record TiledefWithCoords(int x, int y, @Nullable Tiledef tiledef) {
    }
}
