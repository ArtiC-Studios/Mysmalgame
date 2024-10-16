import com.raylib.Jaylib;
import com.raylib.Raylib;
import java.util.ArrayList;
import java.util.List;

import static com.raylib.Jaylib.*;

public class Main {
    public static void main(String[] args) {
        initGame();
    }

    public static void initGame() {

        int screenWidth = 1920;
        int screenHeight = 1000;

        InitWindow(screenWidth, screenHeight, "Game Survive:io");
        SetTargetFPS(60);

        var circlePosition = new com.raylib.Jaylib.Vector2().x(screenWidth / 2.0f).y(screenHeight / 2.0f);
        var ballSpeed = 80.0f;

        // Initiale Geschwindigkeit für die Bälle
        var speed = 1000.0f; // Geschwindigkeit
        List<com.raylib.Jaylib.Vector2> ballPositions = new ArrayList<>();
        List<com.raylib.Jaylib.Vector2> ballDirections = new ArrayList<>();

        while (!WindowShouldClose()) {
            Raylib.Vector2 mousePosition = GetMousePosition();
            BeginDrawing();
            ClearBackground(RAYWHITE);

            float deltaTime = GetFrameTime();

            if (IsKeyDown(68))
                circlePosition.x(circlePosition.x() + ballSpeed * deltaTime);
            if (IsKeyDown(65))
                circlePosition.x(circlePosition.x() - ballSpeed * deltaTime);
            if (IsKeyDown(87))
                circlePosition.y(circlePosition.y() - ballSpeed * deltaTime);
            if (IsKeyDown(83))
                circlePosition.y(circlePosition.y() + ballSpeed * deltaTime);

            DrawCircleV(circlePosition, 10.0f, RED);

            float dx = mousePosition.x() - circlePosition.x();
            float dy = mousePosition.y() - circlePosition.y();
            float distance = (float) Math.sqrt(dx * dx + dy * dy);

            if (distance > 20) {
                float scale = 20 / distance;
                dx *= scale;
                dy *= scale;
            }

            DrawLineEx(new com.raylib.Jaylib.Vector2(circlePosition.x(), circlePosition.y()), new com.raylib.Jaylib.Vector2().x(circlePosition.x() + dx).y(circlePosition.y() + dy), 5.0f, RED);

            if(circlePosition.y() >= 1000){
                circlePosition.y(0);
                System.out.println("You Win");
            }
            if(circlePosition.y() <= -10){
                circlePosition.y(1000);
                System.out.println("You Win");
            }
            if(circlePosition.x() >= 1920){
                circlePosition.x(0);
                System.out.println("You Win");
            }
            if(circlePosition.x() <= -10){
                circlePosition.x(1920);
                System.out.println("You Win");
            }

            System.out.println("Mouse Position: (" + mousePosition.x() + ", " + mousePosition.y() + ")");

            // Ball nur erscheinen lassen, wenn die Leertaste gedrückt wird
            if (IsKeyPressed(KEY_SPACE)) {
                var newBallPosition = new com.raylib.Jaylib.Vector2().x(circlePosition.x() + dx).y(circlePosition.y() + dy);
                var newBallDirection = new com.raylib.Jaylib.Vector2().x(dx / distance).y(dy / distance);
                // Normalisieren der Richtung
                float length = (float) Math.sqrt(newBallDirection.x() * newBallDirection.x() + newBallDirection.y() * newBallDirection.y());
                newBallDirection.x(newBallDirection.x() / length);
                newBallDirection.y(newBallDirection.y() / length);
                ballPositions.add((Jaylib.Vector2) newBallPosition);
                ballDirections.add((Jaylib.Vector2) newBallDirection);
            }

            // Aktualisieren der Positionen der Bälle
            for (int i = 0; i < ballPositions.size(); i++) {
                var ballPosition = ballPositions.get(i);
                var ballDirection = ballDirections.get(i);
                ballPosition.x(ballPosition.x() + ballDirection.x() * speed * deltaTime);
                ballPosition.y(ballPosition.y() + ballDirection.y() * speed * deltaTime);
                DrawCircleV(ballPosition, 5.0f, BLUE);

                if (ballPosition.x() >= 1920) {
                    ballPositions.remove(i);
                    ballDirections.remove(i);
                }
                if (ballPosition.x() <= 0) {
                    ballPositions.remove(i);
                    ballDirections.remove(i);
                }
                if (ballPosition.y() >= 1000) {
                    ballPositions.remove(i);
                    ballDirections.remove(i);
                }
                if (ballPosition.y() <= 0) {
                    ballPositions.remove(i);
                    ballDirections.remove(i);
                }
            }





            EndDrawing();
        }
    }
}