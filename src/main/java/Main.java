import com.raylib.Jaylib;
import com.raylib.Raylib;

public class Main {
    public static void main(String[] args) {
        var tilemap = new Tilemap(10, 10);
        for (var tile : tilemap) {
            System.out.println(tile);
        }

        var sprites = new SpriteList(10);
        sprites.add(0, new SpriteList.Sprite(0, 0, 5, 8, Jaylib.GREEN, null, new Raylib.Rectangle()));
        sprites.add(0, new SpriteList.Sprite(0, 0, 11, 13, Jaylib.RED, null, new Raylib.Rectangle()));
        Raylib.InitWindow(800, 450, "Demo");
        while (!Raylib.WindowShouldClose()) {
            Raylib.ClearBackground(Jaylib.BLACK);
            Raylib.BeginDrawing();
            sprites.drawAll();
            Raylib.EndDrawing();
        }
    }
}
