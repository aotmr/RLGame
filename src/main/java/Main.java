import com.raylib.Raylib;
import state.GameState;
import state.StateMgr;

public class Main {
    public static void main(String[] args) {
        var stateMgr = new StateMgr(new GameState());

        Raylib.SetTargetFPS(60);
        Raylib.InitWindow(800, 600, "window title");

        while (!Raylib.WindowShouldClose()) {
            var frameTime = Raylib.GetFrameTime();
            stateMgr.update(frameTime);

            Raylib.BeginDrawing();
            stateMgr.draw();
            Raylib.DrawFPS(0, 0);
            Raylib.EndDrawing();
        }

        stateMgr.cleanup();
    }
}
