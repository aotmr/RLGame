import com.raylib.Raylib;
import org.jetbrains.annotations.NotNull;

import java.util.AbstractList;
import java.util.ArrayList;

public class Tileset extends AbstractList<Raylib.Rectangle> {
    private final Raylib.Texture texture;
    private final ArrayList<Raylib.Rectangle> rectangles = new ArrayList<>();

    Tileset(@NotNull Raylib.Texture texture, int tilesPerRow, int tilesPerCol) {
        this.texture = texture;
        float tileWidth = (float)texture.width() / tilesPerRow;
        float tileHeight = (float)texture.height() / tilesPerCol;
        for (int i = 0; i < tilesPerRow; ++i) {
            for (int j = 0; j < tilesPerCol; ++j) {
                rectangles.add(
                        new Raylib.Rectangle()
                                .x(i * tileWidth)
                                .y(i * tileHeight)
                                .width(tileWidth)
                                .height(tileHeight));
            }
        }
    }

    @Override
    public Raylib.Rectangle get(int index) {
        return rectangles.get(index);
    }

    @Override
    public int size() {
        return rectangles.size();
    }

    public Raylib.Texture getTexture() {
        return texture;
    }
}
