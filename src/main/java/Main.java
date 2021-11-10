import com.raylib.Jaylib;
import com.raylib.Raylib;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Hello, world!");
        Raylib.InitWindow(800, 450, "Demo");

        while (!Raylib.WindowShouldClose()) {
            Raylib.ClearBackground(Jaylib.BLACK);
            Raylib.BeginDrawing();
            var sprites = new SpriteList(10);
            sprites.add(0, new SpriteList.Sprite(0, 0, 5, 8, Jaylib.GREEN, null));
            sprites.add(0, new SpriteList.Sprite(0, 0, 11, 13, Jaylib.RED, null));
            sprites.drawAll();
            Raylib.EndDrawing();
        }
    }
}
