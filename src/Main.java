import com.raylib.Jaylib;
import com.raylib.Raylib;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.raylib.Jaylib.*;

/**
 * Hauptklasse des Spiels "Survive:io".
 */
public class Main {
    // Standardgeschwindigkeit der Würfel
    private static float cubeSpeed = 100.0f;
    // Mindestabstand zum Spieler, in dem keine Würfel spawnen dürfen
    private static final float MIN_SPAWN_RADIUS = 300.0f;

    /**
     * Hauptmethode, die das Spiel initialisiert.
     * @param args Kommandozeilenargumente
     */
    public static void main(String[] args) {

        int screenWidth = 800;
        int screenHeight = 450;
        InitWindow(screenWidth, screenHeight, "Main Menü Survive:io");
        SetTargetFPS(60);
        var circlePosition = new Jaylib.Vector2().x(screenWidth / 2.0f).y(screenHeight / 2.0f);
        var ballSpeed = 80.0f;
        boolean debugmouse = IsKeyDown(KEY_F9);
        boolean switchState = false;
        while (!WindowShouldClose()) {
            BeginDrawing();
            debugmouse = false;
            Raylib.Vector2 mousePosition = GetMousePosition();
            ClearBackground(GRAY);
            if (IsKeyDown(KEY_F9)) {
                debugmouse = true;
            }
            while (debugmouse) {
                DrawText("MousePos:" + mousePosition.x() + "," + mousePosition.y(), screenWidth / 2, 10, 20, RED);
                break;
            }

            DrawText("To Try the Controlls press F", screenWidth / 2 - MeasureText("To Try the Controlls press F", 15) / 2, screenHeight / 2, 15, WHITE);
            DrawText("Survive", 322, 102, 40, BLACK);
            DrawText("Survive", screenWidth / 2 - MeasureText("Survive:", 20), 100, 40, RED);
            DrawText("Press Enter to start the game", 241, 171, 20, BLACK);
            DrawText("Press Enter to start the game", screenWidth / 2 - MeasureText("Press Enter to start the game", 20) / 2, 170, 20, RED);

            //Würfel zeichnen mit "W"
            DrawRectangle(45, 15, 25, 25, GREEN);
            DrawRectangle(45, 15, 22, 22, WHITE);
            DrawText("W", 52, 20, 15, BLACK);
            while (IsKeyDown(KEY_W)) {
                DrawRectangle(45, 15, 25, 25, LIME);
                DrawRectangle(48, 18, 22, 22, LIGHTGRAY);
                DrawText("W", 54, 22, 15, RED);
                break;
            }

            //Würfel zeichnen mit "S"
            DrawRectangle(45, 45, 25, 25, GREEN);
            DrawRectangle(45, 45, 22, 22, WHITE);
            DrawText("S", 52, 50, 15, BLACK);
            while (IsKeyDown(KEY_S)) {
                DrawRectangle(45, 45, 25, 25, LIME);
                DrawRectangle(48, 48, 22, 22, LIGHTGRAY);
                DrawText("S", 54, 52, 15, RED);

                break;
            }

            //Würfel zeichnen mit "A"
            DrawRectangle(10, 45, 25, 25, GREEN);
            DrawRectangle(10, 45, 22, 22, WHITE);
            DrawText("A", 17, 50, 15, BLACK);
            while (IsKeyDown(KEY_A)) {
                DrawRectangle(10, 45, 25, 25, LIME);
                DrawRectangle(13, 48, 22, 22, LIGHTGRAY);
                DrawText("A", 19, 52, 15, RED);
                break;
            }

            //Würfel zeichnen mit "D"
            DrawRectangle(80, 45, 25, 25, GREEN);
            DrawRectangle(80, 45, 22, 22, WHITE);
            DrawText("D", 87, 50, 15, BLACK);
            while (IsKeyDown(KEY_D)) {
                DrawRectangle(80, 45, 25, 25, LIME);
                DrawRectangle(83, 48, 22, 22, LIGHTGRAY);
                DrawText("D", 89, 52, 15, RED);
                break;
            }

            //Würfel zeichnen mit "SPACE"
            DrawRectangle(15, 80, 85, 25, GREEN);
            DrawRectangle(15, 80, 82, 22, WHITE);
            DrawText("SPACE", 31, 85, 15, BLACK);
            while (IsKeyDown(KEY_SPACE)) {
                DrawRectangle(15, 80, 85, 25, LIME);
                DrawRectangle(18, 83, 82, 22, LIGHTGRAY);
                DrawText("SPACE", 31, 85, 15, LIGHTGRAY);
                DrawText("PENG!", 37, 87, 15, RED);
                break;
            }

            if (IsKeyPressed(KEY_F)) {
                switchState = !switchState;
            }
            if (switchState) {
                // Code für true


                while (switchState == true) {
                    float deltaTime = GetFrameTime();
                    if (IsKeyDown(68))
                            circlePosition.x(circlePosition.x() + ballSpeed * deltaTime);
                    if (IsKeyDown(65))
                            circlePosition.x(circlePosition.x() - ballSpeed * deltaTime);
                    if (IsKeyDown(87))
                            circlePosition.y(circlePosition.y() - ballSpeed * deltaTime);
                    if (IsKeyDown(83))
                            circlePosition.y(circlePosition.y() + ballSpeed * deltaTime);
                    DrawCircleV(circlePosition, 10.0f, BLUE);
                    break;
                }


                // Spielerpositionen überprüfen und anpassen

            } else {
                // Code für false
            }

            /*
            while (!WindowShouldClose() && IsKeyPressed(KEY_E)) {
                float deltaTime = GetFrameTime();
                var circlePosition = new Jaylib.Vector2().x(screenWidth / 2.0f).y(screenHeight / 2.0f);
                var ballSpeed = 80.0f;

                if (IsKeyDown(68))
                    circlePosition.x(circlePosition.x() + ballSpeed * deltaTime);
                if (IsKeyDown(65))
                    circlePosition.x(circlePosition.x() - ballSpeed * deltaTime);
                if (IsKeyDown(87))
                    circlePosition.y(circlePosition.y() - ballSpeed * deltaTime);
                if (IsKeyDown(83))
                    circlePosition.y(circlePosition.y() + ballSpeed * deltaTime);
                DrawCircleV(circlePosition, 10.0f, RED);

                // Spielerpositionen überprüfen und anpassen
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
                break;
            } */


            if (IsKeyPressed(KEY_ENTER)) {
                initGame();
                break;

            }
            EndDrawing();
        }

    }

    /**
     * Initialisiert das Spiel, setzt die Fenstergröße und die Framerate.
     */
    public static void initGame() {
        int screenWidth = 1920;
        int screenHeight = 1000;

        InitWindow(screenWidth, screenHeight, "Game Survive:io");
        SetTargetFPS(60);

        var circlePosition = new Jaylib.Vector2().x(screenWidth / 2.0f).y(screenHeight / 2.0f);
        var ballSpeed = 80.0f;

        // Initiale Geschwindigkeit für die Bälle
        var speed = 1000.0f;
        List<Jaylib.Vector2> ballPositions = new ArrayList<>();
        List<Jaylib.Vector2> ballDirections = new ArrayList<>();
        List<Jaylib.Vector2> cubePositions = new ArrayList<>();
        List<Jaylib.Vector2> cubeDirections = new ArrayList<>();
        Random random = new Random();

        // Timer für das Spawnen der Würfel
        float cubeSpawnTimer = 0;
        int score = 0;
        float cubeSpawnInterval = random.nextFloat() * 2;

        int lives = 10; // Deklaration der Leben außerhalb der Schleife

        while (!WindowShouldClose()) {
            Raylib.Vector2 mousePosition = GetMousePosition();
            BeginDrawing();
            ClearBackground(RAYWHITE);

            float deltaTime = GetFrameTime();

            // Spielerbewegung
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

            DrawLineEx(new Jaylib.Vector2(circlePosition.x(), circlePosition.y()), new Jaylib.Vector2().x(circlePosition.x() + dx).y(circlePosition.y() + dy), 5.0f, RED);

            // Spielerpositionen überprüfen und anpassen
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
                var newBallPosition = new Jaylib.Vector2().x(circlePosition.x() + dx).y(circlePosition.y() + dy);
                var newBallDirection = new Jaylib.Vector2().x(dx / distance).y(dy / distance);
                // Normalisieren der Richtung
                float length = (float) Math.sqrt(newBallDirection.x() * newBallDirection.x() + newBallDirection.y() * newBallDirection.y());
                newBallDirection.x(newBallDirection.x() / length);
                newBallDirection.y(newBallDirection.y() / length);
                ballPositions.add((Jaylib.Vector2) newBallPosition);
                ballDirections.add((Jaylib.Vector2) newBallDirection);
            }

            // Würfel zufällig spawnen lassen
            cubeSpawnTimer -= deltaTime;
            if (cubeSpawnTimer <= 0) {
                float cubeX, cubeY, distanceToPlayer;
                do {
                    cubeX = random.nextFloat() * screenWidth;
                    cubeY = random.nextFloat() * screenHeight;
                    distanceToPlayer = (float) Math.sqrt(Math.pow(cubeX - circlePosition.x(), 2) + Math.pow(cubeY - circlePosition.y(), 2));
                } while (distanceToPlayer < MIN_SPAWN_RADIUS);

                var newCubePosition = new Jaylib.Vector2().x(cubeX).y(cubeY);
                var newCubeDirection = new Jaylib.Vector2().x(circlePosition.x() - cubeX).y(circlePosition.y() - cubeY);
                // Normalisieren der Richtung
                float length = (float) Math.sqrt(newCubeDirection.x() * newCubeDirection.x() + newCubeDirection.y() * newCubeDirection.y());
                newCubeDirection.x(newCubeDirection.x() / length);
                newCubeDirection.y(newCubeDirection.y() / length);
                cubePositions.add((Jaylib.Vector2) newCubePosition);
                cubeDirections.add((Jaylib.Vector2) newCubeDirection);

                // Timer zurücksetzen
                cubeSpawnInterval = random.nextFloat() * 2;
                cubeSpawnTimer = cubeSpawnInterval;
            }

            // Aktualisieren der Positionen der Bälle
            for (int i = 0; i < ballPositions.size(); i++) {
                var ballPosition = ballPositions.get(i);
                var ballDirection = ballDirections.get(i);
                ballPosition.x(ballPosition.x() + ballDirection.x() * speed * deltaTime);
                ballPosition.y(ballPosition.y() + ballDirection.y() * speed * deltaTime);
                DrawCircleV(ballPosition, 5.0f, BLUE);

                if (ballPosition.x() >= 1920 || ballPosition.x() <= 0 || ballPosition.y() >= 1000 || ballPosition.y() <= 0) {
                    ballPositions.remove(i);
                    ballDirections.remove(i);
                }
            }

            // Aktualisieren der Positionen der Würfel
            for (int i = cubePositions.size() - 1; i >= 0; i--) {
                var cubePosition = cubePositions.get(i);
                var cubeDirection = new Jaylib.Vector2().x(circlePosition.x() - cubePosition.x()).y(circlePosition.y() - cubePosition.y());
                // Normalisieren der Richtung
                float length = (float) Math.sqrt(cubeDirection.x() * cubeDirection.x() + cubeDirection.y() * cubeDirection.y());
                cubeDirection.x(cubeDirection.x() / length);
                cubeDirection.y(cubeDirection.y() / length);
                cubePosition.x(cubePosition.x() + cubeDirection.x() * cubeSpeed * deltaTime);
                cubePosition.y(cubePosition.y() + cubeDirection.y() * cubeSpeed * deltaTime);
                DrawRectangle((int)cubePosition.x(), (int)cubePosition.y(), 50, 50, LIME);

                // Debug-Ausgabe für die Position der Würfel
                System.out.println("Würfel Position: (" + cubePosition.x() + ", " + cubePosition.y() + ")");

                if (cubePosition.x() >= 1920 || cubePosition.x() <= 0 || cubePosition.y() >= 1000 || cubePosition.y() <= 0) {
                    cubePositions.remove(i);
                    cubeDirections.remove(i);
                }
            }

            // Kollisionserkennung zwischen Bällen und Würfeln
            for (int i = ballPositions.size() - 1; i >= 0; i--) {
                var ballPosition = ballPositions.get(i);
                for (int j = cubePositions.size() - 1; j >= 0; j--) {
                    var cubePosition = cubePositions.get(j);
                    if (checkCollision(ballPosition, cubePosition, 5, 50)) {
                        ballPositions.remove(i);
                        ballDirections.remove(i);
                        cubePositions.remove(j);
                        cubeDirections.remove(j);
                        ++score;
                        // Erhöhen der Würfelgeschwindigkeit basierend auf dem Punktestand
                        cubeSpeed = 100.0f + score * 5.0f;
                        break;
                    }
                }
            }

            // Leben und Kollisionserkennung zwischen Spieler und Würfeln
            for (int i = cubePositions.size() - 1; i >= 0; i--) {
                if (checkCollisionRectPlayer(circlePosition.x(), circlePosition.y(), 20, 20, cubePositions.get(i).x(), cubePositions.get(i).y(), 50, 50)) {
                    lives--;
                    cubePositions.remove(i); // Rechtecke löschen, die den Spieler berührt haben

                }
            }
            if (lives <= 0) {
                System.out.println("Game Over");
                break;
            }
            DrawText("Lives:" + lives, screenWidth - MeasureText("Lives:" + lives, 20) - 10, 10, 20, RED);

            // Score anzeigen
            DrawText("Score: " + score, 10, 10, 20, RED);
            EndDrawing();
        }
    }

    /**
     * Debug-Methode, die die Position des neu gespawnten Würfels und des Spielers ausgibt.
     * @param cubePosition Position des neu gespawnten Würfels
     * @param circlePosition Position des Spielers
     */
    public static void debugCubeSpawned(Jaylib.Vector2 cubePosition, Jaylib.Vector2 circlePosition) {
        System.out.println("Würfel gespawnt an Position: (" + cubePosition.x() + ", " + cubePosition.y() + ")");
        System.out.println("Cricle gespawnt an Position: (" + circlePosition.x() + "," + circlePosition.y() + ")");
    }

    /**
     * Methode zum Einstellen der Geschwindigkeit der Würfel.
     * @param newSpeed Neue Geschwindigkeit der Würfel
     */
    public static void setCubeSpeed(float newSpeed) {
        cubeSpeed = newSpeed;
    }

    public static boolean checkCollisionRectPlayer(float playerX, float playerY, float playerWidth, float playerHeight,
                                                   float rectX, float rectY, float rectWidth, float rectHeight) {
        return playerX < rectX + rectWidth &&
                playerX + playerWidth > rectX &&
                playerY < rectY + rectHeight &&
                playerY + playerHeight > rectY;
    }

    /**
     * Methode zur Kollisionserkennung zwischen einem Ball und einem Würfel.
     * @param ballPosition Position des Balls
     * @param cubePosition Position des Würfels
     * @param ballRadius Radius des Balls
     * @param cubeSize Größe des Würfels
     * @return true, wenn eine Kollision vorliegt, sonst false
     */
    public static boolean checkCollision(Jaylib.Vector2 ballPosition, Jaylib.Vector2 cubePosition, int ballRadius, int cubeSize) {
        return ballPosition.x() < cubePosition.x() + cubeSize &&
                ballPosition.x() + ballRadius > cubePosition.x() &&
                ballPosition.y() < cubePosition.y() + cubeSize &&
                ballPosition.y() + ballRadius > cubePosition.y();
    }
}